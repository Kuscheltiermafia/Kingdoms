package de.kuscheltiermafia.kingdoms.items.crafting;

import com.google.gson.JsonObject;
import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.utils.GsonHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CraftingHandler {

    static List<BaseRecipe> recipes;

    private final Inventory gui;
    private final Player player;
    private BaseRecipe currentRecipe;

    private ItemStack resultBlocker;

    public CraftingHandler(Player player) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, 9 * 6, ChatColor.WHITE + "Crafting Table");

        setupLayout();
        player.openInventory(gui);
    }

    private void setupLayout() {
        resultBlocker = new ItemBuilder().setCustomName(ChatColor.RED + "No valid Recipe").setID("spacer").setMaterial(Material.BARRIER).temporary(true).build();

        for(int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, ItemHandler.getItem("spacer"));
        }

        for(int i : new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30}) {
            gui.setItem(i, null);
        }

        gui.setItem(23, resultBlocker);
    }

    public void updateOutput() {
        for(BaseRecipe recipe : recipes) {
            if(recipe.matches(gui)) {
                currentRecipe = recipe;
                ItemStack result = recipe.getResult().clone();
                result.setAmount(recipe.resultAmount);
                gui.setItem(23, result);
                return;
            }else{
                gui.setItem(23, resultBlocker);
            }
        }
    }

    public void consumeIngredients() {
        int[] craftingSlots = {10, 11, 12, 19, 20, 21, 28, 29, 30};

        for (int slot : craftingSlots) {
            ItemStack item = gui.getItem(slot);

            if (item == null || item.getType() == Material.AIR) continue;

            int requiredAmount = currentRecipe.getIngredientAmount(item) != 0 ? currentRecipe.getIngredientAmount(item) : 0;

            if (requiredAmount <= 0) continue;

            if (item.getAmount() > requiredAmount) {
                item.setAmount(item.getAmount() - requiredAmount);
            } else {
                gui.setItem(slot, null);
            }
        }
    }

    public void closeInventory() {
        for(int i : new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30}) {
            if(gui.getItem(i) != null) {
                player.getInventory().addItem(gui.getItem(i));
            }
            gui.setItem(i, null);
        }
    }

    public static void initializeRecipes() {
        recipes = new ArrayList<>();
        List<JsonObject> objs = GsonHandler.readJsonsFromFileAsList("recipes/crafting_table", JsonObject.class);
        for(JsonObject json : objs) {
            try {
                BaseRecipe recipe = RecipeTranslator.parse(json);
                if(recipe != null) {
                    recipes.add(recipe);
                    /*
                    System.out.println("Loaded crafting recipe for: " + recipe.result);
                    System.out.println("Pattern: ");
                    recipe.getPattern();
                    */
                }
            } catch (Exception e) {
                Bukkit.getLogger().warning("Failed to load crafting recipe: " + e.getMessage());
            }
        }
    }
}
