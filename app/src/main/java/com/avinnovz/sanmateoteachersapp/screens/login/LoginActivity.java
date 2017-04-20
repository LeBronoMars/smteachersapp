package com.avinnovz.sanmateoteachersapp.screens.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

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

import static com.avinnovz.sanmateoteachersapp.R.id.til_username;

public class LoginActivity extends BaseActivity implements LoginMvp.View{

    @Inject
    LoginPresenter presenter;

    @BindView(til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;


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

        /** use to clear error when typing */
        addDefaultTextWatcher(tilUsername);
        addDefaultTextWatcher(tilPassword);

        tilUsername.getEditText().setText("nedflanders");
        tilPassword.getEditText().setText("P@ssw0rd");
    }

    @OnClick(R.id.btn_login)
    public void login(){
        presenter.onAuthenticateUser(tilUsername.getEditText().getText().toString(),
                tilPassword.getEditText().getText().toString());
    }

    @Override
    public void setUsernameEmptyError() {
        tilUsername.setError(getStringFromResource(R.string.all_message_error_requiredfield));
        tilUsername.getEditText().requestFocus();
    }

    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError(getStringFromResource(R.string.all_message_error_requiredfield));
        tilPassword.getEditText().requestFocus();
    }
}
