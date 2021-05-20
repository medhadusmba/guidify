package com.softmq.guide.app.common.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Preferences implements KeyValueStore<String, String> {
    private final SharedPreferences origin;
    private Context context;

    public Preferences(Context context) {
        origin = PreferenceManager.getDefaultSharedPreferences(context);

    }

    @Override
    public String get(String key) {
        return origin.getString(key, "");
    }

    @Override
    public void put(String key, String value) {
        SharedPreferences.Editor editor = origin.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public boolean has(String key) {
        return origin.getString(key, null) != null;
    }

    @Override
    public void remove(String key) {
        SharedPreferences.Editor editor = origin.edit();
        editor.remove(key);
        editor.apply();
    }
}
