package com.avinnovz.sanmateoteachersapp.screens.main;

import android.os.Bundle;

import com.avinnovz.sanmateoteachersapp.R;
import com.avinnovz.sanmateoteachersapp.base.BaseActivity;
import com.avinnovz.sanmateoteachersapp.base.BaseApplication;
import com.avinnovz.sanmateoteachersapp.screens.main.core.MainMvp;
import com.avinnovz.sanmateoteachersapp.screens.main.core.MainPresenter;
import com.avinnovz.sanmateoteachersapp.screens.main.dagger.DaggerMainComponent;
import com.avinnovz.sanmateoteachersapp.screens.main.dagger.MainModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvp.View {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /** initialize dagger */
        DaggerMainComponent.builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);

    }
}
