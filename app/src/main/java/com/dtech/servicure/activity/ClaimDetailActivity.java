package com.dtech.servicure.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    }
}
