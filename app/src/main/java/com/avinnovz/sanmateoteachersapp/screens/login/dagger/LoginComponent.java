package com.avinnovz.sanmateoteachersapp.screens.login.dagger;

import com.avinnovz.sanmateoteachersapp.dagger.components.AppComponent;
import com.avinnovz.sanmateoteachersapp.dagger.scopes.CustomScope;
import com.avinnovz.sanmateoteachersapp.screens.login.LoginActivity;

import dagger.Component;

/**
 * Created by jayan on 4/14/2017.
 */

@CustomScope
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
