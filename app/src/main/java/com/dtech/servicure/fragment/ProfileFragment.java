package com.dtech.servicure.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dtech.servicure.activity.HomeActivity;
import com.dtech.servicure.databinding.FragmentPendingBinding;
import com.dtech.servicure.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    public static FragmentProfileBinding binding;
    private HomeActivity activity;

    public ProfileFragment(HomeActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.binding.fragmentProfile.setVisibility(View.GONE);
                HomeActivity.binding.viewpager.setVisibility(View.VISIBLE);
            }
        });

    }
}
