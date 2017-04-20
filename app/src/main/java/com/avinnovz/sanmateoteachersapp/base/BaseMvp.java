package com.avinnovz.sanmateoteachersapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

import com.avinnovz.sanmateoteachersapp.data.api.ApiAction;

/**
 * Created by jayan on 4/13/2017.
 */

public interface BaseMvp {

    interface Interactor {
        void attachPresenter(BaseMvp.Presenter presenter);

        boolean isConnected();
    }

    interface View {
        Context getContext();

        boolean hasError();

        void showProgressDialog();

        void dismissProgressDialog();

        void showFailedToConnectError();

        void showSocketTimeoutError();

        void showServerRelatedError();

        void showNoConnectionError();

        void showError(final ApiAction apiAction, final String header, final String errorMessage,
                       final String positiveText, final String negativeText);

        void showToast(int stringId);

        void showToast(String message);

        void moveToOtherActivity(Class clz);

        void moveToOtherActivity(Class clz, Bundle bundle);

        void moveToOtherActivityWithSharedElements(Class clz, android.view.View view, String transitionName);

        void moveToOtherActivityWithSharedElements(Class clz, ActivityOptionsCompat options);

        void finishActivity();
    }

    interface Presenter {
        void attachView(BaseMvp.View view);

        Context getContext();

        void showProgressDialog();

        void dismissProgressDialog();

        void showFailedToConnectError();

        void showSocketTimeoutError();

        void showServerRelatedError();

        void showNoConnectionError();

        void showError(final ApiAction apiAction, final String header, final String errorMessage,
                       final String positiveText, final String negativeText);

        void showToast(int stringId);

        void showToast(String message);

    }

}
