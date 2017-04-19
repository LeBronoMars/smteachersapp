package com.avinnovz.sanmateoteachersapp.screens.login.core;

import com.avinnovz.sanmateoteachersapp.base.BasePresenter;
import com.avinnovz.sanmateoteachersapp.data.api.ApiInterface;
import com.avinnovz.sanmateoteachersapp.helpers.TokenManager;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by jayan on 4/14/2017.
 */

public class LoginPresenter extends BasePresenter implements LoginMvp.Presenter {

    LoginMvp.View view;

    LoginInteractor interactor;

    @Inject
    public LoginPresenter(LoginMvp.View view, Retrofit retrofit, ApiInterface apiInterface) {
        //Attach the view to make it accessible on the BasePresenter
        attachView(view);
        this.view = view;
        this.interactor = new LoginInteractor(this, retrofit, apiInterface);
    }

    @Override
    public void onAuthenticateUser(String username, String password) {
        if (username.isEmpty()) {
            view.setUsernameEmptyError();
        } else if (password.isEmpty()) {
            view.setPasswordEmptyError();
        } else {
            interactor.onAuthenticateUser(username, password);
        }
    }
}
