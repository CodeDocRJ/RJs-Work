package com.dtech.servicure.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.servicure.R;
import com.dtech.servicure.activity.ClaimDetailActivity;
import com.dtech.servicure.databinding.ItemForHomeBinding;
import com.dtech.servicure.model.home.BikerRequest;
import com.dtech.servicure.utils.Animations;
import com.dtech.servicure.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    public interface MyCLickListener {
//        void onAcceptClicked(int claimID, String noteForBoth);
//        void onCancelClicked(int claimID, String noteForBoth);

        void openDialog(int claimID, int executeFor);
    }

    private Activity activity;
    private List<BikerRequest> bikerRequestList = new ArrayList<>();
    private int mExpandedPosition = -1;
    MyCLickListener myCLickListener;

    public HomeAdapter(Activity activity, List<BikerRequest> bikerRequestList, MyCLickListener myCLickListener) {
        this.activity = activity;
        this.bikerRequestList = bikerRequestList;
        this.myCLickListener = myCLickListener;
        for (int i = 0; i < this.bikerRequestList.size(); i++) {
            this.bikerRequestList.get(i).setExpanded(false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemForHomeBinding itemBinding = ItemForHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BikerRequest currData = bikerRequestList.get(position);

        holder.binding.txtName.setText(currData.getInsuredName());
        holder.binding.txtDate.setText(currData.getDateTime());
        holder.binding.txtClaimId.setText(currData.getClaimRefNo());
        holder.binding.txtName1.setText(currData.getInsuredName());
        holder.binding.txtCity.setText(currData.getCity());
        holder.binding.txtMobileNo.setText(currData.getMobileNumber());
        holder.binding.txtSIValue.setText(currData.getSumInsured());
        holder.binding.txtIMEI.setText(currData.getImeiNo());

        if (bikerRequestList.size() > 4) {
            int margin8 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp);
            int margin5 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp);
            int margin105 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._105sdp);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (position == (bikerRequestList.size() - 1)) {
                params.setMargins(margin8, margin5, margin8, margin105);
                holder.binding.linMain.setLayoutParams(params);
            } else {
                params.setMargins(margin8, margin5, margin8, margin5);
                holder.binding.linMain.setLayoutParams(params);
            }
        }

        holder.binding.imgAcceptClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCLickListener.openDialog(currData.getId(), 1);
            }
        });
        holder.binding.imgCancelClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCLickListener.openDialog(currData.getId(), 2);
            }
        });

        holder.binding.linMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ClaimDetailActivity.class);
                intent.putExtra("OBJECT", currData);
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
                boolean show = toggleLayout(!bikerRequestList.get(position).isExpanded(), view, holder.binding.linProcess, position);
                bikerRequestList.get(position).setExpanded(show);
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
        return bikerRequestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemForHomeBinding binding;

        public MyViewHolder(@NonNull ItemForHomeBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
