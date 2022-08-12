package com.dtech.servicure.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dtech.servicure.R;
import com.dtech.servicure.api.RetrofitClient;
import com.dtech.servicure.databinding.ActivityClaimDetailBinding;
import com.dtech.servicure.model.ClaimData;
import com.dtech.servicure.model.ClaimResponse;
import com.dtech.servicure.model.home.BikerRequest;
import com.dtech.servicure.utils.SharePrefs;
import com.dtech.servicure.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaimDetailActivity extends AppCompatActivity {

    private ActivityClaimDetailBinding binding;
    private ClaimDetailActivity activity;
    private BikerRequest bikerRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClaimDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;


        if (getIntent().hasExtra("OBJECT")) {
            bikerRequest = (BikerRequest) getIntent().getSerializableExtra("OBJECT");
        } else {
            Toast.makeText(activity, "Something went wrong.", Toast.LENGTH_SHORT).show();
            finish();
        }

        binding.txtNoteTitle.setText(bikerRequest.getInsuredName() + " " + bikerRequest.getDateTime());
        binding.txtNote.setText(bikerRequest.getNote());
        binding.txtAddress.setText(bikerRequest.getAddressLine1() + "\n" + bikerRequest.getAddressLine2());


        binding.linAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispDialog(1);
            }
        });
        binding.linReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispDialog(2);
            }
        });

    }

    private void dispDialog(int executionFor) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dial_notes);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.findViewById(R.id.txtSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtNote = (EditText) dialog.findViewById(R.id.edtNote);
                Utility.showProgress(activity);
                if (executionFor == 1) {
                    bikerAcceptClaim(edtNote.getText().toString().trim());
                } else if (executionFor == 2) {
                    bikerCanceltClaim(edtNote.getText().toString().trim());
                }
                dialog.dismiss();
            }
        });

        dialog.show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.5f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
        dialog.getWindow().setAttributes(lp);
    }

    private void bikerAcceptClaim(String strNote) {
        int bikerId = SharePrefs.getLoginData().getData().get(0).getId();
        Call<ClaimResponse> call = RetrofitClient.getInstance().getMyApi().callBikerAcceptClaim(new ClaimData(String.valueOf(bikerId), String.valueOf(bikerRequest.getId()), strNote));
        call.enqueue(new Callback<ClaimResponse>() {
            @Override
            public void onResponse(Call<ClaimResponse> call, Response<ClaimResponse> response) {
                Utility.dismissProgress();
                Log.i("CLAIM_ACCEPT", "onResponse() " + response.isSuccessful());
                if (response != null && response.isSuccessful()) {
                    Log.i("CLAIM_ACCEPT", "onResponse() 11 " + response.body().isSuccess());
                }else {
                    Toast.makeText(activity, "Something went wrong!\nPlease try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClaimResponse> call, Throwable t) {
                Utility.dismissProgress();
                Log.i("CLAIM_ACCEPT", "onFailure() Err==> " + t.getLocalizedMessage());
                Toast.makeText(activity, "Something went wrong!\nPlease try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bikerCanceltClaim(String strNote) {
        int bikerId = SharePrefs.getLoginData().getData().get(0).getId();
        Call<ClaimResponse> call = RetrofitClient.getInstance().getMyApi().callBikerCancelClaim(new ClaimData(String.valueOf(bikerId), String.valueOf(bikerRequest.getId()), strNote));
        call.enqueue(new Callback<ClaimResponse>() {
            @Override
            public void onResponse(Call<ClaimResponse> call, Response<ClaimResponse> response) {
                Utility.dismissProgress();
                Log.i("CLAIM_CANCEL", "onResponse() " + response.isSuccessful());
                if (response != null && response.isSuccessful()) {
                    Log.i("CLAIM_CANCEL", "onResponse() 11 " + response.body().isSuccess());
                }
            }

            @Override
            public void onFailure(Call<ClaimResponse> call, Throwable t) {
                Utility.dismissProgress();
                Log.i("CLAIM_CANCEL", "onFailure() Err==> " + t.getLocalizedMessage());
                Toast.makeText(activity, "Something went wrong!\nPlease try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
