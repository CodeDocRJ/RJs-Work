package com.dtech.servicure.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.dtech.servicure.databinding.FragmentHistoryBinding;
import com.dtech.servicure.model.PendingModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HomeActivity activity;

    public HistoryFragment(HomeActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recycHistory.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        ArrayList<PendingModel> pendingModels = HomeActivity.activity.getList();
        PendingAdapter homeAdapter = new PendingAdapter(activity, pendingModels);
        binding.recycHistory.setAdapter(homeAdapter);

        setSelected(0);

        binding.relPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(0);
                setHistoryPickUpData();
            }
        });

        binding.relDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(1);
                setHistoryDeliveryData();
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            Log.i("CHECK_STATE__", "setUserVisibleHint() HistoryFragment.class " + isVisibleToUser);
    }


    private void setHistoryPickUpData() {

    }

    private void setHistoryDeliveryData() {

    }

    private void setSelected(int position) {
        binding.relPickUp.setBackgroundResource(0);
        binding.txtPickup.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.txtPickupNum.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.relDelivery.setBackgroundResource(0);
        binding.txtDelivery.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.txtDeliveryNum.setTextColor(activity.getResources().getColor(R.color.color_grey));

        if (position == 0) {
            binding.relPickUp.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_blue_15));
            binding.txtPickup.setTextColor(activity.getResources().getColor(R.color.white));
            binding.txtPickupNum.setTextColor(activity.getResources().getColor(R.color.white));
        } else {
            binding.relDelivery.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_blue_15));
            binding.txtDelivery.setTextColor(activity.getResources().getColor(R.color.white));
            binding.txtDeliveryNum.setTextColor(activity.getResources().getColor(R.color.white));
        }
    }
}
