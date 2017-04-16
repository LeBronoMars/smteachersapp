package com.avinnovz.sanmateoteachersapp.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jayan on 4/13/2017.
 */

@Module
public class AppModule {

    private Application baseApplication;

    public AppModule(Application baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return baseApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return baseApplication.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }
}
