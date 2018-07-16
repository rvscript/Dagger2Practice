package com.example.ga_mlsdiscovery.dagger2practice.application.app_di;

import com.example.ga_mlsdiscovery.dagger2practice.application.Dagger2Application;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(Dagger2Application app);
}
