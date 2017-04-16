package com.avinnovz.sanmateoteachersapp.screens.login.dagger;

import com.avinnovz.sanmateoteachersapp.dagger.scopes.CustomScope;
import com.avinnovz.sanmateoteachersapp.screens.login.core.LoginMvp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jayan on 4/14/2017.
 */
@Module
public class LoginModule {

    LoginMvp.View view;

    public LoginModule(LoginMvp.View view) { this.view = view; }

    @CustomScope @Provides
    LoginMvp.View provideView(){return view;}

}
