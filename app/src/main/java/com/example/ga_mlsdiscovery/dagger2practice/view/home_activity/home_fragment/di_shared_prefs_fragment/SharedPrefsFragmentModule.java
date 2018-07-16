package com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsFragmentModule {
    private Context context;

    public SharedPrefsFragmentModule(Context context){
        this.context = context;
    }

    @Provides
    @SharedPreferencesScope
    public SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @SharedPreferencesScope
    public MyPrefs provideMyPrefs(){
        return new MyPrefs(provideSharedPreferences());
    }
}
