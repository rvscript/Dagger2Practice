package com.example.ga_mlsdiscovery.dagger2practice.di.components;

import com.example.ga_mlsdiscovery.dagger2practice.di.modules.RetrofitModule;
import com.example.ga_mlsdiscovery.dagger2practice.view.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface HomeActivityComponent {
    void inject(HomeActivity homeActivity);
}
