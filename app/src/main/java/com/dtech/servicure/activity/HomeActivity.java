package com.dtech.servicure.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.dtech.servicure.R;
import com.dtech.servicure.adapter.ViewPagerAdapter;
import com.dtech.servicure.databinding.ActivityHomeBinding;
import com.dtech.servicure.fragment.HistoryFragment;
import com.dtech.servicure.fragment.HomeFragment;
import com.dtech.servicure.fragment.PendingFragment;
import com.dtech.servicure.model.PendingModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    public static HomeActivity activity;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new HomeFragment(activity), "Home");
        viewPagerAdapter.add(new PendingFragment(activity), "Pending");
        viewPagerAdapter.add(new HistoryFragment(activity), "History");

        binding.viewpager.setAdapter(viewPagerAdapter);
//        binding.viewpager.setOffscreenPageLimit(0);

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        binding.linHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(0);
            }
        });

        binding.imgRoundPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(1);
            }
        });
        binding.txtPendingClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgRoundPending.performClick();
            }
        });

        binding.linHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(2);
            }
        });


    }

    /*protected void setFragment(Fragment fragment) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment, fragment);
        t.commit();
    }*/

    private void setSelected(int position) {
        binding.imgHome.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_home_unsel));
        binding.txtHome.setTextColor(activity.getResources().getColor(R.color.color_dim_blue));
        binding.imgRoundPending.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_pending_unsel));
        binding.txtPendingClick.setTextColor(activity.getResources().getColor(R.color.color_dim_blue));
        binding.imgHistory.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_history_unsel));
        binding.txtHistory.setTextColor(activity.getResources().getColor(R.color.color_dim_blue));

        binding.viewpager.setCurrentItem(position);

        if (position == 0) {
            binding.imgHome.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_home_sel));
            binding.txtHome.setTextColor(activity.getResources().getColor(R.color.color_blue));
        } else if (position == 1) {
            binding.imgRoundPending.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_pending_sel));
            binding.txtPendingClick.setTextColor(activity.getResources().getColor(R.color.color_blue));
        } else if (position == 2) {
            binding.imgHistory.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_history_sel));
            binding.txtHistory.setTextColor(activity.getResources().getColor(R.color.color_blue));
        }
    }

    public ArrayList<PendingModel> getList() {
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
