package de.kuscheltiermafia.kingdoms.items.crafting;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BaseRecipe {
    protected final String result;
    protected final int resultAmount;

    public BaseRecipe(String result, int resultAmount) {
        this.result = result;
        this.resultAmount = resultAmount;
    }

    public boolean matches(Inventory inventory) {
        return false;
    }

    public int getIngredientAmount(ItemStack item) {
        return 0;
    }

    public void getPattern() {

    }

    public ItemStack getResult() {
        return ItemHandler.getItem(result);
    }
}
