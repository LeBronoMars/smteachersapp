package com.avinnovz.sanmateoteachersapp.base;

import android.content.Context;

import com.avinnovz.sanmateoteachersapp.data.api.ApiAction;

/**
 * Created by jayan on 4/14/2017.
 */

public class BasePresenter implements BaseMvp.Presenter{

    BaseMvp.View view;

    @Override
    public void attachView(BaseMvp.View view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void showProgressDialog() {
        view.showProgressDialog();
    }

    @Override
    public void dismissProgressDialog() {
        view.dismissProgressDialog();
    }

    @Override
    public void showFailedToConnectError() {
        view.showFailedToConnectError();
    }

    @Override
    public void showSocketTimeoutError() {
        view.showSocketTimeoutError();
    }

    @Override
    public void showServerRelatedError() {
        view.showServerRelatedError();
    }

    @Override
    public void showNoConnectionError() {
        view.showNoConnectionError();
    }

    @Override
    public void showError(ApiAction apiAction, String header, String errorMessage, String positiveText, String negativeText) {
        view.showError(apiAction,header,errorMessage,positiveText,negativeText);
    }

    @Override
    public void showToast(int stringId) {
        view.showToast(stringId);
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

}