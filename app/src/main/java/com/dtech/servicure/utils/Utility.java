package com.dtech.servicure.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dtech.servicure.R;

public class Utility {

    private static Dialog dialog;

    public static void showProgress(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dial_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    public static void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void makeCall(Activity activity, String mobileNumber) {
        Uri number = Uri.parse("tel:" + mobileNumber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        activity.startActivity(callIntent);
    }
}
