package de.kuscheltiermafia.utils;

import com.google.gson.Gson;

public class GsonHandler {
    public static final Gson GSON = new Gson();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
}
