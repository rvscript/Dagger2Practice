package com.example.ga_mlsdiscovery.dagger2practice.application;

import android.app.Application;

import com.example.ga_mlsdiscovery.dagger2practice.BuildConfig;
import com.example.ga_mlsdiscovery.dagger2practice.application.app_di.AppComponent;
import com.example.ga_mlsdiscovery.dagger2practice.application.app_di.AppModule;
import com.example.ga_mlsdiscovery.dagger2practice.application.app_di.DaggerAppComponent;
import com.example.ga_mlsdiscovery.dagger2practice.network.di_network.DaggerInterceptorComponent;
import com.example.ga_mlsdiscovery.dagger2practice.network.di_network.InterceptorModule;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.di_home_activity.DaggerHomeActivityComponent;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.di_home_activity.HomeActivityComponent;
import com.example.ga_mlsdiscovery.dagger2practice.network.di_network.InterceptorComponent;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.di_home_activity.RetrofitModule;
import com.example.ga_mlsdiscovery.dagger2practice.logging.FileLoggingTree;
import com.example.ga_mlsdiscovery.dagger2practice.logging.ReleaseTree;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment.DaggerSharedPrefsFragmentComponent;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment.SharedPrefsFragmentComponent;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment.SharedPrefsFragmentModule;

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
    private SharedPrefsFragmentComponent prefsFragmentComponent;

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

        prefsFragmentComponent = DaggerSharedPrefsFragmentComponent.builder()
                .sharedPrefsFragmentModule(new SharedPrefsFragmentModule(getApplicationContext()))
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

    public SharedPrefsFragmentComponent getPrefsFragmentComponent() {
        return prefsFragmentComponent;
    }
}
