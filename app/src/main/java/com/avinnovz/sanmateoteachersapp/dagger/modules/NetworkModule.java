package com.avinnovz.sanmateoteachersapp.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;

import com.avinnovz.sanmateoteachersapp.data.api.ApiInterface;
import com.avinnovz.sanmateoteachersapp.data.prefs.Prefs;
import com.avinnovz.sanmateoteachersapp.helpers.LogHelper;
import com.avinnovz.sanmateoteachersapp.helpers.TokenManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jayan on 4/13/2017.
 */

@Module
public class NetworkModule {
    private static final int CONNECTION_TIME_OUT = 60;
    private static final int READ_TIME_OUT = 60;
    String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application) {
        /** initialize ok http client */
        File cacheDir = application.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = application.getCacheDir();
        }
        final Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            final Request request = chain.request();
                            LogHelper.log("api","performing url --> " + request.url());
                            return chain.proceed(request);
                        })
                .addInterceptor(interceptor)
                .cache(cache)
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Application application) {
        final Gson gson = new GsonBuilder().setLenient().create();

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(provideOkHttpClient(application))
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public ApiInterface provideApiInterface(Application application) {
        return provideRetrofit(application).create(ApiInterface.class);
    }

    @Provides
    @Singleton
    public TokenManager provideTokenManager() {
        return new TokenManager();
    }

    @Provides
    @Singleton
    public Prefs providePrefs(SharedPreferences sharedPreferences) {
        return new Prefs(sharedPreferences);
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Application application) {
        return new Picasso.Builder(application)
                .executor(Executors.newSingleThreadExecutor())
                .downloader(new OkHttp3Downloader(provideOkHttpClient(application))).build();

    }

}