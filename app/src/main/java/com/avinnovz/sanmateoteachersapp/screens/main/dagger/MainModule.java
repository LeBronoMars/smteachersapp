package com.avinnovz.sanmateoteachersapp.screens.main.dagger;

import com.avinnovz.sanmateoteachersapp.dagger.scopes.CustomScope;
import com.avinnovz.sanmateoteachersapp.screens.main.core.MainMvp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jayan on 4/14/2017.
 */
@Module
public class MainModule {

    MainMvp.View view;

    public MainModule(MainMvp.View view) {
        this.view = view;
    }

    @CustomScope @Provides
    MainMvp.View provideView() { return view; }

}
