package com.avinnovz.sanmateoteachersapp.screens.login;

import android.os.Bundle;
import android.widget.EditText;

import com.avinnovz.sanmateoteachersapp.R;
import com.avinnovz.sanmateoteachersapp.base.BaseActivity;
import com.avinnovz.sanmateoteachersapp.base.BaseApplication;
import com.avinnovz.sanmateoteachersapp.screens.login.core.LoginMvp;
import com.avinnovz.sanmateoteachersapp.screens.login.core.LoginPresenter;
import com.avinnovz.sanmateoteachersapp.screens.login.dagger.DaggerLoginComponent;
import com.avinnovz.sanmateoteachersapp.screens.login.dagger.LoginModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvp.View{

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        /** initialize dagger */
        DaggerLoginComponent.builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);

        etUsername.setText("nedflanders");
        etPassword.setText("P@ssw0rd");
    }

    @OnClick(R.id.btn_login)
    public void login(){
        presenter.onAuthenticateUser(etUsername.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void setUsernameEmptyError() {
        etUsername.setError(getStringFromResource(R.string.all_message_error_requiredfield));
        etUsername.requestFocus();
    }

    @Override
    public void setPasswordEmptyError() {
        etPassword.setError(getStringFromResource(R.string.all_message_error_requiredfield));
        etPassword.requestFocus();
    }
}
