package com.avinnovz.sanmateoteachersapp.screens.main.core;

import com.avinnovz.sanmateoteachersapp.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by jayan on 4/14/2017.
 */

public class MainPresenter extends BasePresenter implements MainMvp.Presenter {

    MainMvp.View view;
    MainInteractor interactor;

    @Inject
    public MainPresenter(MainMvp.View view){
        this.view = view;
        this.interactor = new MainInteractor(this);
        attachView(view);
    }

}
