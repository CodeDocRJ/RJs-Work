package com.dtech.servicure.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.R;
import com.dtech.databinding.ItemPendingBinding;
import com.dtech.servicure.model.PendingModel;

import java.util.ArrayList;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<PendingModel> pendingModels = new ArrayList<>();

    public PendingAdapter(Activity activity, ArrayList<PendingModel> pendingModels) {
        this.activity = activity;
        this.pendingModels = pendingModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPendingBinding itemBinding = ItemPendingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PendingModel currData = pendingModels.get(position);

        holder.binding.txtName.setText(currData.getName());
        holder.binding.txtDate.setText(currData.getDate());

        int margin8 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp);
        int margin5 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp);
        int margin105 = activity.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._105sdp);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (position == (pendingModels.size() - 1)) {
            params.setMargins(margin8, margin5, margin8, margin105);
            holder.binding.linMain.setLayoutParams(params);
        } else {
            params.setMargins(margin8, margin5, margin8, margin5);
            holder.binding.linMain.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return pendingModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemPendingBinding binding;

        public MyViewHolder(@NonNull ItemPendingBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
