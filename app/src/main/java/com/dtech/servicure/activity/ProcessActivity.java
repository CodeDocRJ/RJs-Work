package com.dtech.servicure.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.dtech.R;
import com.dtech.databinding.ActivityLoginBinding;
import com.dtech.databinding.ActivityProcessBinding;
import com.trafi.anchorbottomsheetbehavior.AnchorBottomSheetBehavior;

public class ProcessActivity extends AppCompatActivity {

    private ActivityProcessBinding binding;
    private ProcessActivity activity;
    AnchorBottomSheetBehavior<FrameLayout> behaviour;
    private int displayHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProcessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayHeight = displayMetrics.heightPixels;


        Log.i("SIZE__", "onCreate() displayHeight==> " + displayHeight);
        float totalDp = convertPixelsToDp(displayHeight, ProcessActivity.this);
        Log.i("SIZE__", "onCreate() totalDp==> " + totalDp);

        behaviour = AnchorBottomSheetBehavior.from((FrameLayout) findViewById(R.id.bottom_sheet));
        behaviour.setAllowUserDragging(true);
        behaviour.setHideable(false);
        behaviour.setDisableExpanded(true);
        behaviour.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);
        manageBottomBehaviour();

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void manageBottomBehaviour() {
        this.behaviour.addBottomSheetCallback(new AnchorBottomSheetBehavior.BottomSheetCallback() {
            public void onStateChanged(View view, int oldState, int newState) {
                if (newState == 3) {
                    Log.i("BOTTOM_SHEET", "onStateChanged()  STATE_EXPANDED " + newState);
                } else if (newState == 6) {
                    Log.i("BOTTOM_SHEET", "onStateChanged()  STATE_ANCHORED " + newState);
                } else if (newState == 4) {
                    Log.i("BOTTOM_SHEET", "onStateChanged()  STATE_COLLAPSED " + newState);
                }
            }

            public void onSlide(View view, float offset) {
            }
        });
    }

    public static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}