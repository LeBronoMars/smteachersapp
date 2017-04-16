package com.avinnovz.sanmateoteachersapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.avinnovz.sanmateoteachersapp.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayan on 4/13/2017.
 */

public class CustomProgressDialog extends DialogFragment {
    @BindView(R.id.avi_progress)
    AVLoadingIndicatorView avi_progress;
    private View view;
    private Dialog mDialog;

    public static CustomProgressDialog newInstance() {
        final CustomProgressDialog fragment = new CustomProgressDialog();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.30f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom_progress, null);
        ButterKnife.bind(this, view);
        avi_progress.smoothToShow();
        mDialog = new Dialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        return mDialog;
    }

    @Override
    public void onDestroy() {
        avi_progress.smoothToHide();
        super.onDestroy();
    }
}
