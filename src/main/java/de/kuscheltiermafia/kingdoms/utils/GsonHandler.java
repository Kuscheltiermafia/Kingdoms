package de.kuscheltiermafia.kingdoms.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.crafting.BaseRecipe;
import de.kuscheltiermafia.kingdoms.items.crafting.RecipeTranslator;
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

    public static final Type STAT_MAP_TYPE = new TypeToken<HashMap<Stat, Double>>() {
    }.getType();
    public static final Type ENCHANT_MAP_TYPE = new TypeToken<HashMap<String, Integer>>() {
    }.getType();
    public static final Type ITEM_STAT_MODIFIER_TYPE = new TypeToken<HashMap<Stat, HashMap<String, Double>>>() {
    }.getType();

    public static final Type STRING_LIST_TYPE = new TypeToken<ArrayList<String>>() {}.getType();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Type ofT) {
        return GSON.fromJson(json, ofT);
    }

    public static <T> List<T> readJsonsFromFileAsList(String path, Class<T> typeOfT) {

        ArrayList<T> list = new ArrayList<>();

        try {
            Enumeration<URL> resources = Kingdoms.getPlugin().getClass().getClassLoader().getResources(path);

            T result = typeOfT.getDeclaredConstructor().newInstance();

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if (url.getProtocol().equals("jar")) {
                    String filePath = url.getPath();

                    String jarPath = filePath.substring(5, filePath.indexOf("!"));
                    try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {
                        jar.stream()
                                .filter(entry -> entry.getName().startsWith(path) && entry.getName().endsWith(".json"))
                                .forEach(entry -> {
                                    String storageID = entry.getName().substring(path.length(), entry.getName().length() - 5);

                                    try (InputStream in = Kingdoms.getPlugin().getResource(entry.getName())) {
                                        if (in != null) {
                                            Object obj = GSON.fromJson(new InputStreamReader(in), typeOfT);
                                            list.add((T) obj);
                                        }
                                    } catch (Exception e) {
                                        Kingdoms.getPlugin().getLogger().warning("Failed to load recipe: " + entry.getName());
                                        e.printStackTrace();
                                    }
                                });
                        return list;
                    }catch (Exception e) {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static <T> HashMap<String, T> readJsonsFromFileAsMap(String path, Class<T> typeOfT) {

        HashMap<String, T> map = new HashMap<>();

        try {
            Enumeration<URL> resources = Kingdoms.getPlugin().getClass().getClassLoader().getResources(path);

            T result = typeOfT.getDeclaredConstructor().newInstance();

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if (url.getProtocol().equals("jar")) {
                    String filePath = url.getPath();

                    String jarPath = filePath.substring(5, filePath.indexOf("!"));
                    try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {
                        jar.stream()
                                .filter(entry -> entry.getName().startsWith(path) && entry.getName().endsWith(".json"))
                                .forEach(entry -> {
                                    String storageID = entry.getName().substring(path.length(), entry.getName().length() - 5).replace("/", "");

                                    try (InputStream in = Kingdoms.getPlugin().getResource(entry.getName())) {
                                        if (in != null) {
                                            Object obj = GSON.fromJson(new InputStreamReader(in), typeOfT);
                                            map.put(storageID, (T) obj);
                                        }
                                    } catch (Exception e) {
                                        Kingdoms.getPlugin().getLogger().warning("Failed to load recipe: " + entry.getName());
                                        e.printStackTrace();
                                    }
                                });
                        return map;
                    }catch (Exception e) {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
