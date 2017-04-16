package com.avinnovz.sanmateoteachersapp.screens.main.core;

/**
 * Created by jayan on 4/14/2017.
 */

public class MainInteractor implements MainMvp.Interactor {

    MainPresenter presenter;

    public MainInteractor(MainPresenter presenter) {
        this.presenter = presenter;
    }



}
