package com.dtech.servicure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dtech.servicure.R;
import com.dtech.servicure.api.RetrofitClient;
import com.dtech.servicure.databinding.ActivityLoginBinding;
import com.dtech.servicure.model.LoginData;
import com.dtech.servicure.model.login.LoginModel;
import com.dtech.servicure.utils.SharePrefs;
import com.dtech.servicure.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        Glide.with(activity)
                .load(R.drawable.bike)
//                .placeholder(R.drawable.ic_scooter)
                .into(binding.imgBiker);

        if (SharePrefs.getLoginData() != null) {
            startHomeScreen();
        }


        binding.imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtEmailNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(activity, "Enter Email or Mobile", Toast.LENGTH_SHORT).show();
                } else if (binding.edtPass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(activity, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    Utility.showProgress(activity);
                    callLogin(binding.edtEmailNumber.getText().toString().trim(), binding.edtPass.getText().toString().trim());
                }
            }
        });


    }

    private void startHomeScreen() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void callLogin(String emailMob, String password) {
        Call<LoginModel> call = RetrofitClient.getInstance().getMyApi().callLogin(new LoginData(emailMob, password));
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Utility.dismissProgress();
                Log.i("LOGIN_", "onResponse() " + response.isSuccessful());
                if (response != null && response.isSuccessful()) {
                    Log.i("LOGIN_", "onResponse() 11 " + response.body().isSuccess());
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    SharePrefs.setLoginData(response.body());
                    startHomeScreen();
                } else {
                    Toast.makeText(LoginActivity.this, "Login credentials are invalid.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Utility.dismissProgress();
                Log.i("LOGIN_", "onFailure() Err==> " + t.getLocalizedMessage());
                Toast.makeText(activity, "Something went wrong!\nPlease try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
