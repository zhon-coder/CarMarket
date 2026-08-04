package com.example.carmarket.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferenceUtil {
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "rememberPassword";

    public static void saveLogin(Context ctx, int userId, String username,
                                 String password, boolean remember) {
        SharedPreferences sp = ctx.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        sp.edit().putInt("userId", userId)
                .putString("username",username)
                .putString("password", password)
                .putBoolean("rememberPassword", remember)
                .apply();
    }

    public static void logout(Context ctx) {
        ctx.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

    public static int getUserId(Context ctx) {
        return ctx.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getInt(KEY_USER_ID, -1);
    }

    public static String getUsername(Context ctx) {
        return ctx.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getString(KEY_USERNAME, "");
    }

    public static String getPassword(Context ctx) {
        return ctx.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getString(KEY_PASSWORD, "");
    }

    public static boolean getRememberPassword(Context ctx) {
        return ctx.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                .getBoolean(KEY_REMEMBER, false);
    }
}
