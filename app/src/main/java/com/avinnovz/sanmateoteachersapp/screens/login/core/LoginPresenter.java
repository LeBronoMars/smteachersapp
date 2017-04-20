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
        /** Arrange fields in descending order to prioritize the focus on the view that comes first */
        if (password.isEmpty())  view.setPasswordEmptyError();
        if (username.isEmpty())  view.setUsernameEmptyError();

        if (!view.hasError()){
            interactor.onAuthenticateUser(username, password);
        }
    }
}
