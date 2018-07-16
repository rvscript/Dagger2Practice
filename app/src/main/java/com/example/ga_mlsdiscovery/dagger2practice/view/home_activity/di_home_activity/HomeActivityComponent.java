package com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.di_home_activity;

import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface HomeActivityComponent {
    void inject(HomeActivity homeActivity);
}
