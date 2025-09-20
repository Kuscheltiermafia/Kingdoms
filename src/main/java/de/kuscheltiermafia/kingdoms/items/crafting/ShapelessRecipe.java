package de.kuscheltiermafia.kingdoms.items.crafting;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.stream.Collectors;

public class ShapelessRecipe extends BaseRecipe {
    private final List<RecipeIngredient> ingredients;

    public ShapelessRecipe(List<RecipeIngredient> ingredients, String result, int resultAmount) {
        super(result, resultAmount);
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(Inventory inventory) {
        List<ItemStack> items = Arrays.stream(new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30})
                .mapToObj(inventory::getItem)
                .filter(item -> item != null && item.getType() != Material.AIR)
                .collect(Collectors.toList());

        List<RecipeIngredient> required = new ArrayList<>(ingredients);

        for (ItemStack item : items) {
            String itemId = item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING);
            int itemAmount = item.getAmount();

            Iterator<RecipeIngredient> it = required.iterator();
            while (it.hasNext()) {
                RecipeIngredient ingredient = it.next();
                if (ingredient.getItemId().equals(itemId) && ingredient.getAmount() == itemAmount) {
                    it.remove();
                    break;
                }
            }
        }

        return required.isEmpty();
    }

    @Override
    public int getIngredientAmount(ItemStack item) {
        for (RecipeIngredient ingredient : ingredients) {
            if (ingredient.getItemId().equals(item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING))) {
                return ingredient.getAmount();
            }
        }
        return 0;
    }

    @Override
    public void getPattern() {
        StringBuilder sb = new StringBuilder();
        for(RecipeIngredient ingredient : ingredients) {
            if(!(ingredient == null)) sb.append(" - " + ingredient.getItemId() + " - ");
            else sb.append(" - null - ");
        }
        System.out.println(sb.toString());
    }
}
