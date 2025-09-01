package de.kuscheltiermafia.kingdoms.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.kuscheltiermafia.kingdoms.stats.Stat;

import java.lang.reflect.Type;
import java.util.HashMap;

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
}
