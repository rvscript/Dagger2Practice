package com.example.ga_mlsdiscovery.dagger2practice.di.modules;

import com.example.ga_mlsdiscovery.dagger2practice.network.AuthenticationInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class InterceptorModule {

    @Singleton
    @Provides
    AuthenticationInterceptor provideAuthenticationInterceptor(){
        return new AuthenticationInterceptor();
    }
}
