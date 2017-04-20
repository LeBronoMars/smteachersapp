package com.avinnovz.sanmateoteachersapp.textwatchers;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by jayan on 4/20/2017.
 */

public class BaseTextWatcher implements TextWatcher {
    protected TextInputLayout textInputLayout;

    public BaseTextWatcher(TextInputLayout textInputLayout){
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(textInputLayout.getTag() == null){
            textInputLayout.setTag("dirty");
        }
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
