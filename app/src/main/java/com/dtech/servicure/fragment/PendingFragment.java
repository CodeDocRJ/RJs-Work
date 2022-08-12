package com.dtech.servicure.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.servicure.R;
import com.dtech.servicure.activity.HomeActivity;
import com.dtech.servicure.adapter.HomeAdapter;
import com.dtech.servicure.adapter.PendingAdapter;
import com.dtech.servicure.api.RetrofitClient;
import com.dtech.servicure.databinding.FragmentPendingBinding;
import com.dtech.servicure.model.home.BikerRequest;
import com.dtech.servicure.model.home.HomePickupModel;
import com.dtech.servicure.model.pending.BikerPickupPending;
import com.dtech.servicure.model.pending.PendingPickupModel;
import com.dtech.servicure.utils.SharePrefs;
import com.dtech.servicure.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingFragment extends Fragment {

    private FragmentPendingBinding binding;
    private HomeActivity activity;
    private int pageIndexPickup;


    public PendingFragment(HomeActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPendingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("CHECK_STATE__", "onViewCreated() PendingFragment.class " );

        binding.recycPending.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

        setSelected(0);

        binding.relPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(0);

            }
        });

        binding.relDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(1);

            }
        });

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        Log.i("CHECK_STATE__", "setUserVisibleHint() PendingFragment.class " + isVisibleToUser);
    }


    private void setPendingPickUpData() {
        Utility.showProgress(activity);
        Call<PendingPickupModel> call = RetrofitClient.getInstance().getMyApi().getPendingPickupList(String.valueOf(SharePrefs.getLoginData().getData().get(0).getId()), String.valueOf(pageIndexPickup));
        call.enqueue(new Callback<PendingPickupModel>() {
            @Override
            public void onResponse(Call<PendingPickupModel> call, Response<PendingPickupModel> response) {
                Log.i("HOME_PICK_", "onResponse() " + response.isSuccessful());
                if (response != null && response.isSuccessful()) {
                    Log.i("HOME_PICK_", "onResponse() " + response.body().getTotalBikerPickupPending());

                    List<BikerPickupPending> bikerPickupPending = response.body().getBikerPickupPending();
                    PendingAdapter pendingAdapter = new PendingAdapter(activity, bikerPickupPending);
                    binding.recycPending.setAdapter(pendingAdapter);
                    Utility.dismissProgress();
                }
            }

            @Override
            public void onFailure(Call<PendingPickupModel> call, Throwable t) {
                Utility.dismissProgress();
                Log.i("HOME_PICK_", "onFailure() " + t.getLocalizedMessage());
                Toast.makeText(activity, "Something went wrong!\nPlease try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setPendingDeliveryData() {

    }

    private void setSelected(int position) {
        binding.relPickUp.setBackgroundResource(0);
        binding.txtPickup.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.txtPickupNum.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.relDelivery.setBackgroundResource(0);
        binding.txtDelivery.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.txtDeliveryNum.setTextColor(activity.getResources().getColor(R.color.color_grey));

        if (position == 0) {
            setPendingPickUpData();
            binding.relPickUp.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_blue_15));
            binding.txtPickup.setTextColor(activity.getResources().getColor(R.color.white));
            binding.txtPickupNum.setTextColor(activity.getResources().getColor(R.color.white));
        } else {
            setPendingDeliveryData();
            binding.relDelivery.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_blue_15));
            binding.txtDelivery.setTextColor(activity.getResources().getColor(R.color.white));
            binding.txtDeliveryNum.setTextColor(activity.getResources().getColor(R.color.white));
        }
    }
}
