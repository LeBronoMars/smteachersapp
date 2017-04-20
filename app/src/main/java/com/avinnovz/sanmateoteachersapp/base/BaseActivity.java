package com.avinnovz.sanmateoteachersapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avinnovz.sanmateoteachersapp.R;
import com.avinnovz.sanmateoteachersapp.data.api.ApiAction;
import com.avinnovz.sanmateoteachersapp.dialogs.CustomProgressDialog;
import com.avinnovz.sanmateoteachersapp.interfaces.OnConfirmDialogListener;
import com.avinnovz.sanmateoteachersapp.textwatchers.BaseTextWatcher;
import com.rey.material.app.Dialog;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by jayan on 4/13/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseMvp.View {
    private CustomProgressDialog progressDialog;
    private Toast toast;
    ArrayList<String> errors;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) progressDialog.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public boolean hasError(){
        errors = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();
        findAllTextInputLayout(viewGroup);
        if (errors.size() > 0)
            return true;
        else return false;
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.newInstance();
            progressDialog.setCancelable(false);
            progressDialog.show(getFragmentManager(), "custom progress");
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showFailedToConnectError() {
        showConfirmDialog("", getStringFromResource(R.string.all_title_error_connection),
                getStringFromResource(R.string.all_message_error_unabletoconnect),
                getStringFromResource(R.string.all_button_close), "", null, true);
    }

    @Override
    public void showSocketTimeoutError() {
        showConfirmDialog("", getStringFromResource(R.string.all_title_error_connection),
                getStringFromResource(R.string.all_message_error_sockettimeout),
                getStringFromResource(R.string.all_button_close), "", null, true);
    }

    @Override
    public void showServerRelatedError() {
        showConfirmDialog("", getStringFromResource(R.string.all_title_error_connection),
                getStringFromResource(R.string.all_message_error_servererror),
                getStringFromResource(R.string.all_button_close), "", null, true);
    }

    @Override
    public void showNoConnectionError() {
        showConfirmDialog("", getStringFromResource(R.string.all_title_error_connection),
                getStringFromResource(R.string.all_message_error_noconnection),
                getStringFromResource(R.string.all_button_close), "", null, true);
    }

    @Override
    public void showError(ApiAction apiAction, String header, String errorMessage,
                          String positiveText, String negativeText) {
        showConfirmDialog(null, header, errorMessage, positiveText, negativeText, null, true);
    }

    @Override
    public void showToast(int stringId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), getStringFromResource(stringId),
                Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void moveToOtherActivity(Class clz) {
        startActivity(new Intent(this, clz));
        animateToLeft(this);
    }

    @Override
    public void moveToOtherActivity(Class clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
        animateToLeft(this);
    }

    @Override
    public void moveToOtherActivityWithSharedElements(Class clz, View view, String transitionName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, (View) view, transitionName);
            startActivity(new Intent(this, clz), options.toBundle());
        } else {
            startActivity(new Intent(this, clz));
        }
    }

    @Override
    public void moveToOtherActivityWithSharedElements(Class clz, ActivityOptionsCompat options) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(new Intent(this, clz), options.toBundle());
        } else {
            startActivity(new Intent(this, clz));
        }
    }

    @Override
    public void finishActivity() {
        finish();
        animateToRight(this);
    }

    protected String getStringFromResource(int stringId) {
        return getResources().getString(stringId);
    }

    private void animateToLeft(Activity activity) {
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    private void animateToRight(Activity activity) {
        activity.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    public void showConfirmDialog(final String action, final String title, final String message,
                                  final String positiveText, final String negativeText,
                                  final OnConfirmDialogListener onConfirmDialogListener,
                                  final boolean cancelable) {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(title);
        dialog.setContentView(R.layout.dialog_confirm);
        ((TextView) dialog.findViewById(R.id.tv_message)).setText(message);
        final Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        dialog.layoutParams((int) (size.x * .80), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        if (!positiveText.isEmpty()) {
            dialog.positiveAction(positiveText);
            dialog.positiveActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            dialog.positiveActionClickListener(v -> {
                dialog.dismiss();
                if (onConfirmDialogListener != null) {
                    onConfirmDialogListener.onConfirmed(action);
                }
            });
        }
        dialog.negativeAction(negativeText);
        dialog.negativeActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dialog.negativeActionClickListener(v -> {
            dialog.dismiss();
            if (onConfirmDialogListener != null) {
                onConfirmDialogListener.onCancelled(action);
            }
        });
        dialog.show();
    }

    protected void addDefaultTextWatcher(TextInputLayout textInputLayout){
        textInputLayout.getEditText().addTextChangedListener(new BaseTextWatcher(textInputLayout));
    }

    protected void findAllTextInputLayout(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup && !(view instanceof TextInputLayout))
                findAllTextInputLayout((ViewGroup) view);
            else if (view instanceof TextInputLayout) {
                TextInputLayout textInputLayout = (TextInputLayout) view;
                if (textInputLayout.getError() != null)
                    errors.add(textInputLayout.getError().toString());
            }
        }
    }

}
