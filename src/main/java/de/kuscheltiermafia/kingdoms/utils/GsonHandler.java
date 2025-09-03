package de.kuscheltiermafia.kingdoms.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.Kingdoms;
import de.kuscheltiermafia.kingdoms.items.ItemBuilder;
import de.kuscheltiermafia.kingdoms.stats.Stat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarFile;

public class GsonHandler {
    public static final Gson GSON = new Gson();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    public static HashMap<Stat, Double> returnItemStatMap(String json) {
        Type type = new TypeToken<HashMap<Stat, Double>>() {}.getType();
        return GSON.fromJson(json, type);
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
}
