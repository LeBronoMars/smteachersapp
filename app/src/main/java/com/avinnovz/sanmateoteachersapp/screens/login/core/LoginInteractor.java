package com.avinnovz.sanmateoteachersapp.screens.login.core;

import com.avinnovz.sanmateoteachersapp.base.BaseApplication;
import com.avinnovz.sanmateoteachersapp.base.BaseInteractor;
import com.avinnovz.sanmateoteachersapp.data.api.ApiAction;
import com.avinnovz.sanmateoteachersapp.data.api.ApiInterface;
import com.avinnovz.sanmateoteachersapp.data.api.ApiRequestHelper;
import com.avinnovz.sanmateoteachersapp.data.api.OnApiRequestListener;
import com.avinnovz.sanmateoteachersapp.helpers.LogHelper;
import com.avinnovz.sanmateoteachersapp.helpers.TokenManager;
import com.avinnovz.sanmateoteachersapp.models.response.GenericResponse;
import com.avinnovz.sanmateoteachersapp.models.response.Token;
import com.avinnovz.sanmateoteachersapp.screens.login.dagger.DaggerLoginComponent;
import com.avinnovz.sanmateoteachersapp.screens.login.dagger.LoginModule;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jayan on 4/14/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginMvp.Interactor, OnApiRequestListener {

    @Inject
    TokenManager tokenManager;

    @Inject
    Retrofit retrofit;

    @Inject
    ApiInterface apiInterface;

    LoginPresenter presenter;
    ApiRequestHelper requestHelper;

    public LoginInteractor(LoginPresenter presenter) {
        DaggerLoginComponent.builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                .loginModule(new LoginModule(null))
                .build()
                .inject(this);

        //Attach the presenter to make it accessible on the BaseInteractor
        attachPresenter(presenter);
        this.presenter = presenter;
        this.requestHelper = new ApiRequestHelper(this, retrofit, apiInterface);
    }

    @Override
    public void onApiRequestStart(ApiAction apiAction) {
        presenter.showProgressDialog();
    }

    @Override
    public void onApiRequestFailed(ApiAction apiAction, Throwable t) {
        presenter.dismissProgressDialog();
        LogHelper.log("err", "action --> " + apiAction.name() + " cause --> " + t.getMessage());
        if (t.getMessage().contains("failed to connect") || t.getMessage().contains("Failed to connect")) {
            presenter.showFailedToConnectError();
        } else if (t.getMessage().contains("timeout")) {
            presenter.showSocketTimeoutError();
        } else if (t.getMessage().contains("No address associated with hostname") ||
                t.getMessage().contains("Unable to resolve host")) {
            presenter.showNoConnectionError();
        } else {
            if (t instanceof HttpException) {
                final HttpException ex = ((HttpException) t);
                try {
                    final String json = ex.response().errorBody().string();
                    final GenericResponse genericResponse = requestHelper.parseError(GenericResponse.class, json);
                    presenter.showError(null, "Login", genericResponse.getMessage(), "Close", null);
                } catch (IOException e) {

                }
            }
        }
    }

    @Override
    public void onApiRequestSuccess(ApiAction apiAction, Object result) {
        presenter.dismissProgressDialog();
        if (apiAction.equals(ApiAction.POST_LOGIN)) {
            LogHelper.log("api", "token manager is null " + (tokenManager == null));
            LogHelper.log("api", "token --> " + ((Token) result).getToken());
            tokenManager.setToken((Token) result);
            presenter.showToast("Successful login");
        }
    }

    @Override
    public void onAuthenticateUser(String username, String password) {
        if (isConnected()) {
            requestHelper.login(username, password);
        } else {
            presenter.showNoConnectionError();
        }
    }
}
