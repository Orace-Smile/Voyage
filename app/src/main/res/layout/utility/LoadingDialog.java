package bj.orace.street.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import bj.orace.street.R;
import bj.orace.street.databinding.DialogLayoutBinding;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialog(Activity activity){
        this.activity = activity;
    }

    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.loadingDialogStyle);
        DialogLayoutBinding binding = DialogLayoutBinding.inflate(LayoutInflater.from(activity),null,false);
        builder.setView(binding.getRoot());
        builder.setCancelable(false);
        alertDialog = builder.create();
//        binding.loadingRotion.start();
        alertDialog.show();
    }

    public void stopLoading(){
        alertDialog.dismiss();
    }

}
