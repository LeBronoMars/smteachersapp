package com.avinnovz.sanmateoteachersapp.screens.main.dagger;

import com.avinnovz.sanmateoteachersapp.dagger.components.AppComponent;
import com.avinnovz.sanmateoteachersapp.dagger.scopes.CustomScope;
import com.avinnovz.sanmateoteachersapp.screens.main.MainActivity;

import dagger.Component;

/**
 * Created by jayan on 4/14/2017.
 */
@CustomScope
@Component(dependencies = AppComponent.class,
        modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
