package bj.orace.voyage.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import bj.orace.voyage.constant.AllConstant;

public class AppPermission {

    public boolean isStorageOk(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestStoragePermission(Activity activity){
        ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE ,
                Manifest.permission.WRITE_EXTERNAL_STORAGE} , AllConstant.STORAGE_REQUEST_CODE);
    }
}