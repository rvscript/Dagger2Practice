package com.example.ga_mlsdiscovery.dagger2practice.application;

import android.content.Context;

import com.example.ga_mlsdiscovery.dagger2practice.logging.FileLoggingTree;
import com.example.ga_mlsdiscovery.dagger2practice.logging.ReleaseTree;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    FileLoggingTree provideFileLoggingTree(){
        return (new FileLoggingTree(context));
    }

    @Singleton
    @Provides
    ReleaseTree provideReleaseTree(){
        return (new ReleaseTree(context));
    }
}
