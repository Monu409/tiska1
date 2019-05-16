package com.app.tiskakarate;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class Check_permissions {

  static String[] PERMISSIONS = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static boolean hasPermissions(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity != null && PERMISSIONS != null){
            for (String permission : PERMISSIONS) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    public static void request_permissions(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(PERMISSIONS, 1);
        }
    }
}
