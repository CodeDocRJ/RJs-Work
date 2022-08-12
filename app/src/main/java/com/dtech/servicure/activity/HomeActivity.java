package com.dtech.servicure.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.dtech.servicure.R;
import com.dtech.servicure.adapter.ViewPagerAdapter;
import com.dtech.servicure.databinding.ActivityHomeBinding;
import com.dtech.servicure.fragment.HistoryFragment;
import com.dtech.servicure.fragment.HomeFragment;
import com.dtech.servicure.fragment.PendingFragment;
import com.dtech.servicure.fragment.ProfileFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static ActivityHomeBinding binding;
    public static HomeActivity activity;

//    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

//        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPagerAdapter.add(new HomeFragment(activity), "Home");
//        viewPagerAdapter.add(new PendingFragment(activity), "Pending");
//        viewPagerAdapter.add(new HistoryFragment(activity), "History");

//        binding.viewpager.setAdapter(viewPagerAdapter);
//        binding.viewpager.setOffscreenPageLimit(0);

        /*binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        });*/

        setSelected(0);

        binding.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fragmentProfile.setVisibility(View.VISIBLE);
//                binding.viewpager.setVisibility(View.GONE);
                binding.frameLayout.setVisibility(View.GONE);
                setFragment(new ProfileFragment(activity));
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

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragmentProfile, fragment);
        t.commit();
    }

    private void setSelected(int position) {
        binding.imgHome.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_home_unsel));
        binding.txtHome.setTextColor(activity.getResources().getColor(R.color.color_dim_blue));
        binding.imgRoundPending.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_pending_unsel));
        binding.txtPendingClick.setTextColor(activity.getResources().getColor(R.color.color_dim_blue));
        binding.imgHistory.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_history_unsel));
        binding.txtHistory.setTextColor(activity.getResources().getColor(R.color.color_dim_blue));

        if (position == 0) {
            loadFragment(new HomeFragment(activity));
            binding.imgHome.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_home_sel));
            binding.txtHome.setTextColor(activity.getResources().getColor(R.color.color_blue));
        } else if (position == 1) {
            loadFragment(new PendingFragment(activity));
            binding.imgRoundPending.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_pending_sel));
            binding.txtPendingClick.setTextColor(activity.getResources().getColor(R.color.color_blue));
        } else if (position == 2) {
            loadFragment(new HistoryFragment(activity));
            binding.imgHistory.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_history_sel));
            binding.txtHistory.setTextColor(activity.getResources().getColor(R.color.color_blue));
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.fragmentProfile.getVisibility() == View.VISIBLE) {
            ProfileFragment.binding.imgBack.performClick();
            return;
        }
        super.onBackPressed();
    }

}
