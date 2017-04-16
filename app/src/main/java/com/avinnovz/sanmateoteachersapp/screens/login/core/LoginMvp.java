package com.avinnovz.sanmateoteachersapp.screens.login.core;

import com.avinnovz.sanmateoteachersapp.base.BaseMvp;

/**
 * Created by jayan on 4/14/2017.
 */

public interface LoginMvp {

    interface Interactor extends BaseMvp.Interactor{
        void onAuthenticateUser(final String username, final String password);
    }

    interface View extends BaseMvp.View{
        void setUsernameEmptyError();
        void setPasswordEmptyError();
    }

    interface Presenter extends BaseMvp.Presenter{
        void onAuthenticateUser(String username, String password);
    }

}
