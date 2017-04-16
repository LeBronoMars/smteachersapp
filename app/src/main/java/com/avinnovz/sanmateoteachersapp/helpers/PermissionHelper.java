package com.avinnovz.sanmateoteachersapp.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by jayan on 4/13/2017.
 */

public class PermissionHelper {

    public static void setupPermissions(Activity activity) {
        //TODO:: Put all needed permissions here.
//        String[] permissionStorage = {
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//        };
//
//        String[] permissionCamera = {
//                Manifest.permission.CAMERA};
//
//        checkIfPermissionsAreGranted(permissionStorage, activity);
//        checkIfPermissionsAreGranted(permissionCamera, activity);
    }

    public static void checkIfPermissionsAreGranted(String[] permissionList, Activity activity) {
        final int PERMISSION_REQUEST_ID = 0;

        for (String permission : permissionList) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED)
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(activity,
                            permissionList,
                            PERMISSION_REQUEST_ID);
                }

            break;
        }
    }

}
