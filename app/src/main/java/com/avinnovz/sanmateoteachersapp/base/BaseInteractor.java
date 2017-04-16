package com.avinnovz.sanmateoteachersapp.base;

import com.avinnovz.sanmateoteachersapp.helpers.NetworkHelper;

/**
 * Created by jayan on 4/16/2017.
 */

public class BaseInteractor implements BaseMvp.Interactor {

    BaseMvp.Presenter presenter;

    @Override
    public void attachPresenter(BaseMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isConnected() {
        return NetworkHelper.isConnected(presenter.getContext());
    }

}
