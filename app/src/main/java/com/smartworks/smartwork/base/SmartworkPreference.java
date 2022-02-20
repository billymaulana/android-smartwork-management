package com.smartworks.smartwork.base;

import android.content.Context;
import android.content.SharedPreferences;

public class SmartworkPreference {
    private static final String PREFERENCE_NAME = "Smartwork";
    private static final String TOKEN = "token";
    private static final String NAME = "name";
    private SharedPreferences sharedpreferences;

    public SmartworkPreference (Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return sharedpreferences.getString(TOKEN, null);
    }


    public void saveName(String name) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NAME, name);
        editor.apply();
    }

    public String getName() {
        return sharedpreferences.getString(NAME, null);
    }

}
