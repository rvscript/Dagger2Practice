package com.example.ga_mlsdiscovery.dagger2practice.di.modules;

import com.example.ga_mlsdiscovery.dagger2practice.network.RetrofitService;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    RetrofitService provideRetrofitService(){
        return new RetrofitService();
    }
}
