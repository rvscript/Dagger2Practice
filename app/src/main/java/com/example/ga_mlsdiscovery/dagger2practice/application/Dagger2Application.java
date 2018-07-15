package com.example.ga_mlsdiscovery.dagger2practice.application;

import android.app.Application;

import com.example.ga_mlsdiscovery.dagger2practice.BuildConfig;
import com.example.ga_mlsdiscovery.dagger2practice.di.components.DaggerHomeActivityComponent;
import com.example.ga_mlsdiscovery.dagger2practice.di.components.DaggerInterceptorComponent;
import com.example.ga_mlsdiscovery.dagger2practice.di.components.HomeActivityComponent;
import com.example.ga_mlsdiscovery.dagger2practice.di.components.InterceptorComponent;
import com.example.ga_mlsdiscovery.dagger2practice.di.modules.InterceptorModule;
import com.example.ga_mlsdiscovery.dagger2practice.di.modules.RetrofitModule;
import com.example.ga_mlsdiscovery.dagger2practice.logging.FileLoggingTree;
import com.example.ga_mlsdiscovery.dagger2practice.logging.ReleaseTree;

import javax.inject.Inject;

import timber.log.Timber;

public class Dagger2Application extends Application {
    @Inject
    ReleaseTree releaseTree;
    @Inject
    FileLoggingTree fileLoggingTree;

    private static Dagger2Application app;
    private HomeActivityComponent homeActivityComponent;
    private InterceptorComponent interceptorComponent;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        homeActivityComponent = DaggerHomeActivityComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();

        interceptorComponent = DaggerInterceptorComponent.builder()
                .interceptorModule(new InterceptorModule())
                .build();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

        //adding dagger implementation to Application class
        app.getAppComponent().inject(this);
        //Adding Timber logging
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            Timber.plant(fileLoggingTree);
        } else {
            Timber.plant(releaseTree);
        }

    }

    public static Dagger2Application getApp() {
        return app;
    }

    public HomeActivityComponent getHomeActivityComponent() {
        return homeActivityComponent;
    }

    public InterceptorComponent getInterceptorComponent() {
        return interceptorComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
