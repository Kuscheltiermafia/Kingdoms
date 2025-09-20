package de.kuscheltiermafia.kingdoms.items.crafting;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ShapedRecipe extends BaseRecipe{
    private final List<List<RecipeIngredient>> pattern;

    public ShapedRecipe(List<List<RecipeIngredient>> pattern, String result, int resultAmount) {
        super(result, resultAmount);
        this.pattern = pattern;
    }

    @Override
    public boolean matches(Inventory inventory) {
        int[] craftingSlots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
        int gridSize = 3;
        int patternRows = pattern.size();
        int patternCols = pattern.get(0).size();

        for (int rowOffset = 0; rowOffset <= gridSize - patternRows; rowOffset++) {
            for (int colOffset = 0; colOffset <= gridSize - patternCols; colOffset++) {

                boolean match = true;

                for (int row = 0; row < patternRows; row++) {
                    for (int col = 0; col < patternCols; col++) {
                        int slotIndex = (rowOffset + row) * gridSize + (colOffset + col);
                        int inventorySlot = craftingSlots[slotIndex];

                        ItemStack item = inventory.getItem(inventorySlot);
                        RecipeIngredient ingredient = pattern.get(row).get(col);

                        if (ingredient == null) {
                            if (item != null && item.getType() != Material.AIR) {
                                match = false;
                                break;
                            }
                            continue;
                        }

                        if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) {
                            match = false;
                            break;
                        }

                        ItemMeta meta = item.getItemMeta();
                        if (meta == null) {
                            match = false;
                            break;
                        }

                        String actualId = meta.getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING);
                        String expectedId = ingredient.getItemId();

                        int actualAmount = item.getAmount();
                        int expectedAmount = ingredient.getAmount();

                        if (!expectedId.equals(actualId) || actualAmount < expectedAmount) {
                            match = false;
                            break;
                        }
                    }
                    if (!match) break;
                }
                if (match) return true;
            }
        }
        return false;
    }

    @Override
    public int getIngredientAmount(ItemStack item) {
        for(List<RecipeIngredient> row : pattern) {
            for(RecipeIngredient ingredient : row) {
                if(ingredient != null && item.getItemMeta().getPersistentDataContainer().get(ItemHandler.ID, PersistentDataType.STRING).equals(ingredient.getItemId())) {
                    return ingredient.getAmount();
                }
            }
        }
        return 0;
    }

    @Override
    public void getPattern() {
        for(List<RecipeIngredient> row : pattern) {
            StringBuilder sb = new StringBuilder();
            for(RecipeIngredient ingredient : row) {
                if(!(ingredient == null)) sb.append(" - " + ingredient.getItemId() + " - ");
                else sb.append(" - null - ");
            }
            System.out.println(sb.toString());
        }
    }
}
