package com.example.urban_food.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    public static void putBooleanPref(String key, Boolean value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBooleanPref(String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static void putStringPref(String key, String value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPref(String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void clearPrefs(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}