package com.dtech.servicure.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.servicure.activity.PendingProcessActivity;
import com.dtech.servicure.databinding.ItemForPendingBinding;
import com.dtech.servicure.model.home.BikerRequest;
import com.dtech.servicure.model.pending.BikerPickupPending;
import com.dtech.servicure.utils.Animations;
import com.dtech.servicure.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder> {

    private Activity activity;
    private List<BikerPickupPending> bikerPickupPending = new ArrayList<>();
    private int mExpandedPosition = -1;

    public PendingAdapter(Activity activity, List<BikerPickupPending> bikerPickupPending) {
        this.activity = activity;
        this.bikerPickupPending = bikerPickupPending;
        for (int i = 0; i < this.bikerPickupPending.size(); i++) {
            this.bikerPickupPending.get(i).setExpanded(false);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemForPendingBinding itemBinding = ItemForPendingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BikerPickupPending currData = bikerPickupPending.get(position);

        holder.binding.txtName.setText(currData.getInsuredName());
        holder.binding.txtDate.setText(currData.getDateTime());
        holder.binding.txtClaimId.setText(currData.getClaimRefNo());
        holder.binding.txtName1.setText(currData.getInsuredName());
        holder.binding.txtCity.setText(currData.getCity());
        holder.binding.txtMobileNo.setText(currData.getMobileNumber());
        holder.binding.txtSIValue.setText(currData.getSumInsured());
        holder.binding.txtIMEI.setText(currData.getImeiNo());

        if (bikerPickupPending.size() > 4) {
            int margin8 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp);
            int margin5 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp);
            int margin105 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._105sdp);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (position == (bikerPickupPending.size() - 1)) {
                params.setMargins(margin8, margin5, margin8, margin105);
                holder.binding.linMain.setLayoutParams(params);
            } else {
                params.setMargins(margin8, margin5, margin8, margin5);
                holder.binding.linMain.setLayoutParams(params);
            }
        }

        holder.binding.linMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PendingProcessActivity.class);
                activity.startActivity(intent);
            }
        });
        holder.binding.imgMakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.makeCall(activity, currData.getMobileNumber());
            }
        });

        if (position == mExpandedPosition) {
            holder.binding.linProcess.setVisibility(View.VISIBLE);
        } else {
            holder.binding.linProcess.setVisibility(View.GONE);
        }

        holder.binding.imgExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean show = toggleLayout(!bikerPickupPending.get(position).isExpanded(), view, holder.binding.linProcess, position);
                bikerPickupPending.get(position).setExpanded(show);
            }
        });
    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand, int position) {
        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
            mExpandedPosition = position;
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;
    }

    @Override
    public int getItemCount() {
        return bikerPickupPending.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemForPendingBinding binding;

        public MyViewHolder(@NonNull ItemForPendingBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}