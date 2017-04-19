package com.avinnovz.sanmateoteachersapp.base;

import android.app.Application;

import com.avinnovz.sanmateoteachersapp.BuildConfig;
import com.avinnovz.sanmateoteachersapp.R;
import com.avinnovz.sanmateoteachersapp.dagger.components.AppComponent;
import com.avinnovz.sanmateoteachersapp.dagger.components.DaggerAppComponent;
import com.avinnovz.sanmateoteachersapp.dagger.modules.AppModule;
import com.avinnovz.sanmateoteachersapp.dagger.modules.NetworkModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by jayan on 4/13/2017.
 */

public class  BaseApplication extends Application {

    private static BaseApplication baseApplication = new BaseApplication();
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /** initialize AppComponent */
        getAppComponent();

        /** initialize Calligraphy */
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule(BuildConfig.HOST_NAME))
                    .build();
        }
        return appComponent;
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }
}
