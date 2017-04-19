package com.avinnovz.sanmateoteachersapp;

import com.avinnovz.sanmateoteachersapp.data.api.ApiInterface;
import com.avinnovz.sanmateoteachersapp.models.request.User;
import com.avinnovz.sanmateoteachersapp.models.response.Token;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by rsbulanon on 4/19/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiTest {

    protected ApiInterface apiInterface;

    @Before
    public void setup() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        apiInterface = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST_NAME)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiInterface.class);
    }

    @Test
    public void authenticateUser() {
        final User user = new User("nedflanders", "qwerty");
        final Observable<Token> observable = apiInterface.login(user);

        final TestSubscriber<Token> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

        final Token accessToken = testSubscriber.getOnNextEvents().get(0);

        /** assert that Token object is not null */
        Assert.assertNotNull(accessToken);

        /** assert that token.getToken() is not equal to 'hello world' */
        Assert.assertNotEquals("hello world", accessToken.getToken());
    }
}
