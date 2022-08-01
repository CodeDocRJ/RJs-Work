package com.dtech.servicure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.databinding.ActivityHomeBinding;
import com.dtech.servicure.adapter.PendingAdapter;
import com.dtech.servicure.model.PendingModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeActivity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        binding.recycPendings.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        ArrayList<PendingModel> pendingModels = getList();
        PendingAdapter pendingAdapter = new PendingAdapter(activity, pendingModels);
        binding.recycPendings.setAdapter(pendingAdapter);


        binding.linHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(activity, ProcessActivity.class);
//                startActivity(intent);
            }
        });


    }

    private ArrayList<PendingModel> getList() {
        ArrayList<PendingModel> newList = new ArrayList<>();

        newList.add(new PendingModel("Patty O'Furniture", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Olive Yew", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Aida Bugg", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Maureen Biologist", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Teri Dactyl", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Peg", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Allie Grater", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Liz Erd", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Minnie Van Ryder", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("A. Mused", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("P. Ann O’Recital", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Ray O’Sun", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Isabelle Ringing", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Chris Anthemum", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Paige Turner", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Harriet Upp", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Anita Letterback", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Audie Yose", "07:15, 03/06/2022", false));
        newList.add(new PendingModel("Bea Mine", "07:15, 03/06/2022", false));

        return newList;
    }
}
