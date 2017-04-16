package com.avinnovz.sanmateoteachersapp.dagger.components;

import android.app.Application;

import com.avinnovz.sanmateoteachersapp.dagger.modules.AppModule;
import com.avinnovz.sanmateoteachersapp.dagger.modules.NetworkModule;
import com.avinnovz.sanmateoteachersapp.data.api.ApiInterface;
import com.avinnovz.sanmateoteachersapp.data.prefs.Prefs;
import com.avinnovz.sanmateoteachersapp.helpers.TokenManager;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by jayan on 4/13/2017.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)
public interface AppComponent {
    ApiInterface apiInterface();
    Application application();
    Retrofit retrofit();
    TokenManager tokenManager();
    Prefs prefs();
    Picasso picasso();
}
