package de.kuscheltiermafia.kingdoms.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.items.crafting.BaseRecipe;
import de.kuscheltiermafia.kingdoms.items.crafting.RecipeTranslator;
import de.kuscheltiermafia.kingdoms.items.itemEnchants.ItemEnchant;
import de.kuscheltiermafia.kingdoms.stats.Stat;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarFile;

public class GsonHandler {
    public static final Gson GSON = new Gson();

    public static final Type STAT_MAP_TYPE = new TypeToken<HashMap<Stat, Double>>() {}.getType();
    public static final Type ENCHANT_MAP_TYPE = new TypeToken<HashMap<String, Integer>>() {}.getType();
    public static final Type ITEM_STAT_MODIFIER_TYPE = new TypeToken<HashMap<Stat, HashMap<String, Double>>>() {}.getType();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Type ofT) {
        return GSON.fromJson(json, ofT);
    }

    public static HashMap<String, ItemBuilder> returnStoredItems() {
        HashMap<String, ItemBuilder> items = new HashMap<>();

        try {
            Enumeration<URL> resources = Kingdoms.getPlugin().getClass().getClassLoader().getResources("items");

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();

                if (url.getProtocol().equals("jar")) {
                    String path = url.getPath();
                    String jarPath = path.substring(5, path.indexOf("!"));
                    try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {
                        jar.stream()
                                .filter(entry -> entry.getName().startsWith("items/") && entry.getName().endsWith(".json"))
                                .forEach(entry -> {
                                    String itemId = entry.getName().substring("items/".length(), entry.getName().length() - 5);
                                    try (InputStream in = Kingdoms.getPlugin().getResource(entry.getName())) {
                                        if (in != null) {
                                            ItemBuilder item = GSON.fromJson(new InputStreamReader(in), ItemBuilder.class);
                                            items.put(itemId, item);
                                        }
                                    } catch (Exception e) {
                                        Kingdoms.getPlugin().getLogger().warning("Failed to load item: " + entry.getName());
                                        e.printStackTrace();
                                    }
                                });
                    }
                }
            }
        } catch (IOException e) {
            Kingdoms.getPlugin().getLogger().severe("Error loading custom items: " + e.getMessage());
            e.printStackTrace();
        }
        return items;
    }

    public static List<BaseRecipe> returnCraftingTableRecipes() {
        List<BaseRecipe> recipes = new ArrayList<>();
        try {
            Enumeration<URL> resources = Kingdoms.getPlugin().getClass().getClassLoader().getResources("recipes/crafting_table");

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();

                if (url.getProtocol().equals("jar")) {
                    String path = url.getPath();
                    String jarPath = path.substring(5, path.indexOf("!"));
                    try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {
                        jar.stream()
                                .filter(entry -> entry.getName().startsWith("recipes/crafting_table") && entry.getName().endsWith(".json"))
                                .forEach(entry -> {
                                    String itemId = entry.getName().substring("recipes/crafting_table".length(), entry.getName().length() - 5);
                                    try (InputStream in = Kingdoms.getPlugin().getResource(entry.getName())) {
                                        if (in != null) {
                                            JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(in)).getAsJsonObject();
                                            recipes.add(RecipeTranslator.parse(jsonObject));
                                        }
                                    } catch (Exception e) {
                                        Kingdoms.getPlugin().getLogger().warning("Failed to load recipe: " + entry.getName());
                                        e.printStackTrace();
                                    }
                                });
                    }
                }
            }
        } catch (IOException e) {
            Kingdoms.getPlugin().getLogger().severe("Error loading custom items: " + e.getMessage());
            e.printStackTrace();
        }
        return recipes;
    }
}
