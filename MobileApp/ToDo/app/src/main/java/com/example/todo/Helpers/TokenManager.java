package com.example.todo.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private final SharedPreferences sharedPreferences;
    private static final String TOKEN_KEY = "jwt_token";

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences("my_app", Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, "");
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
