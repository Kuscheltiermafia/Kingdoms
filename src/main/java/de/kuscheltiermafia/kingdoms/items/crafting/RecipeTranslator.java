package de.kuscheltiermafia.kingdoms.items.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeTranslator {
    public static BaseRecipe parse(JsonObject json) {
        String type = json.get("type").getAsString();

        if (type.equals("shaped")) return parseShaped(json);
        if (type.equals("shapeless")) return parseShapeless(json);

        throw new IllegalArgumentException("Unknown recipe type: " + type);
    }

    private static ShapedRecipe parseShaped(JsonObject json) {
        JsonArray patternArray = json.getAsJsonArray("pattern");
        List<List<RecipeIngredient>> pattern = new ArrayList<>();

        for (JsonElement row : patternArray) {
            JsonArray rowArray = row.getAsJsonArray();
            List<RecipeIngredient> rowItems = new ArrayList<>();
            for (JsonElement cell : rowArray) {
                if (cell.isJsonNull()) {
                    rowItems.add(null);
                } else {
                    rowItems.add(RecipeIngredient.fromString(cell.getAsString()));
                }
            }
            pattern.add(rowItems);
        }

        JsonObject resultObj = json.getAsJsonObject("result");
        String resultId = resultObj.get("item").getAsString();
        int resultAmount = resultObj.get("amount").getAsInt();

        return new ShapedRecipe(pattern, resultId, resultAmount);
    }

    private static ShapelessRecipe parseShapeless(JsonObject json) {
        JsonArray ingredientsArray = json.getAsJsonArray("ingredients");
        List<RecipeIngredient> ingredients = new ArrayList<>();

        for (JsonElement el : ingredientsArray) {
            ingredients.add(RecipeIngredient.fromString(el.getAsString()));
        }

        JsonObject resultObj = json.getAsJsonObject("result");
        String resultId = resultObj.get("item").getAsString();
        int resultAmount = resultObj.get("amount").getAsInt();

        return new ShapelessRecipe(ingredients, resultId, resultAmount);
    }
}
