package com.avinnovz.sanmateoteachersapp.helpers;

import android.util.Log;

import com.avinnovz.sanmateoteachersapp.BuildConfig;

/**
 * Created by jayan on 4/13/2017.
 */

public class LogHelper {

    public static void log(final String key, final String message) {
        if (BuildConfig.DEBUG) {
            Log.d(key, message);
        }
    }
}
