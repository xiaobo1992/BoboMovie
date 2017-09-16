package com.bobo.normalman.bobomovie.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 9/14/17.
 */

public class ModelUtil {
    static Gson gson = new Gson();
    private static String SP_NAME = "MODEL";

    public static void save(Context context, String key, Object obj) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
        String val = gson.toJson(obj);
        sp.edit().putString(key, val).apply();
    }

    public static <T> T read(Context context, String key, TypeToken<T> token) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
        String obj = sp.getString(key, "");
        try {
            return gson.fromJson(obj, token.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toObject(String val, TypeToken<T> token) {
        return gson.fromJson(val, token.getType());
    }

    public static <T> String toString(T obj, TypeToken<T> token) {
        return gson.toJson(obj, token.getType());
    }
}
