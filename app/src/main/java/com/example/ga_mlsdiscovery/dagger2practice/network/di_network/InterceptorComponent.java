package com.example.ga_mlsdiscovery.dagger2practice.network.di_network;

import com.example.ga_mlsdiscovery.dagger2practice.network.RetrofitService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {InterceptorModule.class})
public interface InterceptorComponent {
    void inject(RetrofitService retrofitService);
}
