package com.dtech.servicure.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dtech.servicure.R;
import com.dtech.servicure.databinding.ActivityClaimDetailBinding;
import com.dtech.servicure.databinding.ActivityPendingProcessBinding;

public class ClaimDetailActivity extends AppCompatActivity {

    private ActivityClaimDetailBinding binding;
    private ClaimDetailActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClaimDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        binding.linAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispDialog();
            }
        });

    }

    private void dispDialog() {
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.dial_notes, viewGroup, false);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(dialogView);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
//        lp.dimAmount = 0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
//        alertDialog.getWindow().setAttributes(lp);
//        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);


        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dial_notes);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.5f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
        dialog.getWindow().setAttributes(lp);
    }
}
