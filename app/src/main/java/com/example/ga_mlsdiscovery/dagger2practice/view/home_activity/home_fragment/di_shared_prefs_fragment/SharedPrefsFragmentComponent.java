package com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment;

import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.SharePreferencesFragment;

import dagger.Component;

@SharedPreferencesScope
@Component(modules = {SharedPrefsFragmentModule.class})
public interface SharedPrefsFragmentComponent {
    void inject(SharePreferencesFragment sharePreferencesFragment);
}
