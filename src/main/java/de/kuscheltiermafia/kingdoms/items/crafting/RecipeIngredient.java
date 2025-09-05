package de.kuscheltiermafia.kingdoms.items.crafting;

public class RecipeIngredient {
    private final String itemId;
    private final int amount;

    public RecipeIngredient(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public static RecipeIngredient fromString(String input) {
        String[] parts = input.split(":");
        String id = parts[0];
        int amount = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
        return new RecipeIngredient(id, amount);
    }
}
