package com.avinnovz.sanmateoteachersapp.screens.login.core;

import com.avinnovz.sanmateoteachersapp.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by jayan on 4/14/2017.
 */

public class LoginPresenter extends BasePresenter implements LoginMvp.Presenter {

    LoginMvp.View view;

    LoginInteractor interactor;

    @Inject
    public LoginPresenter(LoginMvp.View view) {
        //Attach the view to make it accessible on the BasePresenter
        attachView(view);
        this.view = view;
        this.interactor = new LoginInteractor(this);
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
