package com.dtech.servicure.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.dtech.servicure.api.RetrofitClient;
import com.dtech.servicure.databinding.FragmentHomeBinding;
import com.dtech.servicure.model.ClaimData;
import com.dtech.servicure.model.ClaimResponse;
import com.dtech.servicure.model.home.BikerRequest;
import com.dtech.servicure.model.home.HomePickupModel;
import com.dtech.servicure.utils.SharePrefs;
import com.dtech.servicure.utils.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeActivity activity;
    int pageIndexPickup = 0;

    public HomeFragment() {
    }

    public HomeFragment(HomeActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("CHECK_STATE__", "onViewCreated() HomeFragment.class ");

        binding.recycHomePickUp.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        binding.recycHomeDeliv.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

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
            Log.i("CHECK_STATE__", "setUserVisibleHint() HomeFragment.class " + isVisibleToUser);
    }

    private void setHomePickUpData() {
        Utility.showProgress(activity);
        Call<HomePickupModel> call = RetrofitClient.getInstance().getMyApi().getHomePickupList(String.valueOf(SharePrefs.getLoginData().getData().get(0).getId()), String.valueOf(pageIndexPickup));
        call.enqueue(new Callback<HomePickupModel>() {
            @Override
            public void onResponse(Call<HomePickupModel> call, Response<HomePickupModel> response) {
                Log.i("HOME_PICK_", "onResponse() " + response.isSuccessful());
                if (response != null && response.isSuccessful()) {
                    Log.i("HOME_PICK_", "onResponse() " + response.body().getTotalBikerRequest());

                    List<BikerRequest> bikerRequestList = response.body().getBikerRequest();
                    HomeAdapter homeAdapter = new HomeAdapter(activity, bikerRequestList, new HomeAdapter.MyCLickListener() {
                        @Override
                        public void openDialog(int claimID, int executeFor) {
                            openMyDialog(claimID, executeFor);
                        }
                    });
                    binding.recycHomePickUp.setAdapter(homeAdapter);
                    Utility.dismissProgress();
                }
            }

            @Override
            public void onFailure(Call<HomePickupModel> call, Throwable t) {
                Utility.dismissProgress();
                Log.i("HOME_PICK_", "onFailure() " + t.getLocalizedMessage());
                Toast.makeText(activity, "Something went wrong!\nPlease try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openMyDialog(int claimID, int executionFor) {
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
                    callAcceptClaim(claimID, edtNote.getText().toString().trim());
                } else if (executionFor == 2) {
                    callCancelClaim(claimID, edtNote.getText().toString().trim());
                }
                dialog.dismiss();
            }
        });

        dialog.show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.5f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
        dialog.getWindow().setAttributes(lp);
    }


    private void callAcceptClaim(int claimID, String strNote) {
        int bikerId = SharePrefs.getLoginData().getData().get(0).getId();
        Call<ClaimResponse> call = RetrofitClient.getInstance().getMyApi().callBikerAcceptClaim(new ClaimData(String.valueOf(bikerId), String.valueOf(claimID), strNote));
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

    private void callCancelClaim(int claimID, String strNote) {
        int bikerId = SharePrefs.getLoginData().getData().get(0).getId();
        Call<ClaimResponse> call = RetrofitClient.getInstance().getMyApi().callBikerCancelClaim(new ClaimData(String.valueOf(bikerId), String.valueOf(claimID), strNote));
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

    private void setHomeDeliveryData() {

    }

    private void setSelected(int position) {
        binding.relPickUp.setBackgroundResource(0);
        binding.txtPickup.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.txtPickupNum.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.relDelivery.setBackgroundResource(0);
        binding.txtDelivery.setTextColor(activity.getResources().getColor(R.color.color_grey));
        binding.txtDeliveryNum.setTextColor(activity.getResources().getColor(R.color.color_grey));

        if (position == 0) {
            binding.recycHomeDeliv.setVisibility(View.GONE);
            binding.recycHomePickUp.setVisibility(View.VISIBLE);
            setHomePickUpData();
            binding.relPickUp.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_blue_15));
            binding.txtPickup.setTextColor(activity.getResources().getColor(R.color.white));
            binding.txtPickupNum.setTextColor(activity.getResources().getColor(R.color.white));
        } else {
            binding.recycHomePickUp.setVisibility(View.GONE);
            binding.recycHomeDeliv.setVisibility(View.VISIBLE);
            setHomeDeliveryData();
            binding.relDelivery.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_blue_15));
            binding.txtDelivery.setTextColor(activity.getResources().getColor(R.color.white));
            binding.txtDeliveryNum.setTextColor(activity.getResources().getColor(R.color.white));
        }


    }

}
