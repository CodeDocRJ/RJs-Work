package com.dtech.servicure.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dtech.servicure.databinding.ActivityPendingProcessBinding;
import com.dtech.servicure.databinding.ActivityTakeSignBinding;
import com.dtech.servicure.utils.MyFileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakeSignActivity extends AppCompatActivity {

    private ActivityTakeSignBinding binding;
    private TakeSignActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTakeSignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        binding.signatureView.setPathColor(Color.BLACK);
        binding.signatureView.setWidth(400.0);

        binding.signatureView.signatureClear();
        File file = new File(MyFileUtils.getImageFile(activity), "image_sign.png");
        if (file.exists()) {
            boolean isDeleted = file.delete();
        }


        binding.txtClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.signatureView.signatureClear();
            }
        });

        binding.txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.signatureView.isSignature()) {
                    Bitmap bitmap = binding.signatureView.getSignatureBitmap(false);
                    String signPath = createFileFromBitmap(bitmap);

                    if (new File(signPath).exists() && new File(signPath).length() > 0) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("signPath", signPath);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_CANCELED, returnIntent);
                        finish();
                    }

                } else {
                    Toast.makeText(activity, "You have to sign first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String createFileFromBitmap(Bitmap bitmap) {
        //create a file to write bitmap data
        File file = new File(MyFileUtils.getImageFile(activity), "image_sign.png");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("FILE__", "createFileFromBitmap() Err==> " + e.getLocalizedMessage());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("FILE__", "createFileFromBitmap() Err 2==> " + e.getLocalizedMessage());
        }
        Log.i("FILE__", "createFileFromBitmap() filePAth==> " + file.getAbsolutePath());
        return file.getAbsolutePath();
    }
}
