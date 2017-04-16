package com.avinnovz.sanmateoteachersapp.data.api;

import com.avinnovz.sanmateoteachersapp.helpers.LogHelper;
import com.avinnovz.sanmateoteachersapp.models.request.User;
import com.avinnovz.sanmateoteachersapp.models.response.Token;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jayan on 4/14/2017.
 */

public class ApiRequestHelper {
    private OnApiRequestListener onApiRequestListener;
    private Retrofit retrofit;
    private ApiInterface apiInterface;

    public ApiRequestHelper(OnApiRequestListener onApiRequestListener) {
        this.onApiRequestListener = onApiRequestListener;
    }

    public ApiRequestHelper(OnApiRequestListener onApiRequestListener, Retrofit retrofit, ApiInterface apiInterface) {
        this.onApiRequestListener = onApiRequestListener;
        this.retrofit = retrofit;
        this.apiInterface = apiInterface;
    }

    /**
     * handle api result using lambda
     *
     * @param action     identification of the current api request
     * @param observable actual process of the api request
     */
    private void handleObservableResult(final ApiAction action, final Observable observable) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(result -> onApiRequestListener.onApiRequestSuccess(action, result),
                        throwable -> onApiRequestListener.onApiRequestFailed(action, (Throwable) throwable),
                        () -> LogHelper.log("api", "api request completed --> " + action));
    }

    public <T> T parseError(final Class clazz, Response<?> response) {
        final Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(clazz,
                new Annotation[0]);
        T error = null;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            LogHelper.log("err", "error in mapping ---> " + e.getMessage());
        }
        return error;
    }

    public <T> T parseError(final Class clazz, final String json) {
        return (T) new Gson().fromJson(json, clazz);
    }

    /*****************APP SPECIFIC REQUESTS *****************/
    public void login(final String username, final String password) {
        onApiRequestListener.onApiRequestStart(ApiAction.POST_LOGIN);
        final Observable<Token> observable = apiInterface.login(new User(username,password));
        handleObservableResult(ApiAction.POST_LOGIN, observable);
    }

}
