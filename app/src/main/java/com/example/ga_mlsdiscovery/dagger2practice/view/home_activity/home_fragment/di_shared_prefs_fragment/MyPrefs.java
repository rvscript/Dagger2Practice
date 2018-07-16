package com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment;

import android.content.SharedPreferences;

public class MyPrefs {
    private SharedPreferences preferences;

    public MyPrefs(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void putPreferences(String key, String value){
        preferences.edit().putString(key, value).apply();
    }

    public String getPreferences(String key, String value){
        return preferences.getString(key, value);
    }
}
