package com.dtech.servicure.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.dtech.servicure.R;
import com.dtech.servicure.databinding.ActivityPendingProcessBinding;
import com.dtech.servicure.utils.MyFileUtils;
import com.trafi.anchorbottomsheetbehavior.AnchorBottomSheetBehavior;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PendingProcessActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_1 = 101;
    private static final int CAMERA_REQUEST_2 = 202;
    private static final int CAMERA_REQUEST_3 = 303;
    private static final int CAMERA_REQUEST_4 = 404;
    private static final int CAMERA_REQUEST_5 = 505;
    private static final int CAMERA_REQUEST_6 = 606;
    private static final int CODE_TAKE_SIGN = 1001;
    private ActivityPendingProcessBinding binding;
    private PendingProcessActivity activity;
    AnchorBottomSheetBehavior<FrameLayout> behaviour;
    private int displayHeight;
    boolean isPermissionGiven = false;
    //    ArrayList<String> selectedFilePath = new ArrayList<>();
    String filePath1 = "", filePath2 = "", filePath3 = "", filePath4 = "", filePath5 = "", filePath6 = "";
    private Bitmap mySignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendingProcessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayHeight = displayMetrics.heightPixels;


        Log.i("SIZE__", "onCreate() displayHeight==> " + displayHeight);
        float totalDp = convertPixelsToDp(displayHeight, activity);
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

        if (isWriteReadPermission()) {
            isPermissionGiven = true;
        }

        binding.incProcess.imgPick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteReadPermission()) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_1);
                }
            }
        });

        binding.incProcess.imgPick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteReadPermission()) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_2);
                }
            }
        });
        binding.incProcess.imgPick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteReadPermission()) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_3);
                }
            }
        });
        binding.incProcess.imgPick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteReadPermission()) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_4);
                }
            }
        });
        binding.incProcess.imgPick5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteReadPermission()) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_5);
                }
            }
        });
        binding.incProcess.imgPick6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteReadPermission()) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_6);
                }
            }
        });

        binding.incProcess.imgCancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FILE__", "DELETE() 11 filePath==> " + filePath1);
                Glide.with(activity).load(R.drawable.ic_camera_place).into(binding.incProcess.imgPick1);
                if (new File(filePath1).exists()) {
                    new File(filePath1).delete();
                }
                filePath1 = "";
                binding.incProcess.imgCancel1.setVisibility(View.GONE);
            }
        });
        binding.incProcess.imgCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FILE__", "DELETE() 22 filePath==> " + filePath2);
                Glide.with(activity).load(R.drawable.ic_camera_place).into(binding.incProcess.imgPick2);
                if (new File(filePath2).exists()) {
                    new File(filePath2).delete();
                }
                filePath2 = "";
                binding.incProcess.imgCancel2.setVisibility(View.GONE);
            }
        });
        binding.incProcess.imgCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FILE__", "DELETE() 33 filePath==> " + filePath2);
                Glide.with(activity).load(R.drawable.ic_camera_place).into(binding.incProcess.imgPick3);
                if (new File(filePath3).exists()) {
                    new File(filePath3).delete();
                }
                filePath3 = "";
                binding.incProcess.imgCancel3.setVisibility(View.GONE);
            }
        });
        binding.incProcess.imgCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(activity).load(R.drawable.ic_camera_place).into(binding.incProcess.imgPick4);
                if (new File(filePath4).exists()) {
                    new File(filePath4).delete();
                }
                filePath4 = "";
                binding.incProcess.imgCancel4.setVisibility(View.GONE);
            }
        });
        binding.incProcess.imgCancel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(activity).load(R.drawable.ic_camera_place).into(binding.incProcess.imgPick5);
                if (new File(filePath5).exists()) {
                    new File(filePath5).delete();
                }
                filePath5 = "";
                binding.incProcess.imgCancel5.setVisibility(View.GONE);
            }
        });
        binding.incProcess.imgCancel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(activity).load(R.drawable.ic_camera_place).into(binding.incProcess.imgPick6);
                if (new File(filePath6).exists()) {
                    new File(filePath6).delete();
                }
                filePath6 = "";
                binding.incProcess.imgCancel6.setVisibility(View.GONE);
            }
        });


        binding.incProcess.relGetSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TakeSignActivity.class);
                startActivityForResult(intent, CODE_TAKE_SIGN);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_1 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath1 = createFileFromBitmap(photo, CAMERA_REQUEST_1);
            Log.i("FILE__", "onActivityResult() 11 filePath==> " + filePath1);
            Glide.with(activity).load(photo).into(binding.incProcess.imgPick1);
            binding.incProcess.imgCancel1.setVisibility(View.VISIBLE);

        } else if (requestCode == CAMERA_REQUEST_2 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath2 = createFileFromBitmap(photo, CAMERA_REQUEST_2);
            Log.i("FILE__", "onActivityResult() 22 filePath==> " + filePath2);
            Glide.with(activity).load(photo).into(binding.incProcess.imgPick2);
            binding.incProcess.imgCancel2.setVisibility(View.VISIBLE);

        } else if (requestCode == CAMERA_REQUEST_3 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath5 = createFileFromBitmap(photo, CAMERA_REQUEST_3);
            Glide.with(activity).load(photo).into(binding.incProcess.imgPick3);
            binding.incProcess.imgCancel3.setVisibility(View.VISIBLE);

        } else if (requestCode == CAMERA_REQUEST_4 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath4 = createFileFromBitmap(photo, CAMERA_REQUEST_4);
            Glide.with(activity).load(photo).into(binding.incProcess.imgPick4);
            binding.incProcess.imgCancel4.setVisibility(View.VISIBLE);

        } else if (requestCode == CAMERA_REQUEST_5 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath5 = createFileFromBitmap(photo, CAMERA_REQUEST_5);
            Glide.with(activity).load(photo).into(binding.incProcess.imgPick5);
            binding.incProcess.imgCancel5.setVisibility(View.VISIBLE);

        } else if (requestCode == CAMERA_REQUEST_6 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath6 = createFileFromBitmap(photo, CAMERA_REQUEST_6);
            Glide.with(activity).load(photo).into(binding.incProcess.imgPick6);
            binding.incProcess.imgCancel6.setVisibility(View.VISIBLE);

        } else if (requestCode == CODE_TAKE_SIGN) {
            if (resultCode == Activity.RESULT_OK) {
                String signPath = data.getStringExtra("signPath");
                Log.i("FILE__", "onActivityResult() signPath==> " + signPath);
                /*Glide.with(activity)
                        .load(new File(signPath))
                        .into(binding.incProcess.imgSign);*/

                Bitmap bitmap = BitmapFactory.decodeFile(signPath);
                binding.incProcess.imgSign.setImageBitmap(bitmap);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(activity, "Somthing went wrong,\nSignature is not captured", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String createFileFromBitmap(Bitmap bitmap, int cameraReqCode) {
        //create a file to write bitmap data
        File file = new File(MyFileUtils.getImageFile(activity), "image_" + cameraReqCode + ".jpeg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("FILE__", "createFileFromBitmap() Err==> " + e.getLocalizedMessage());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("FILE__", "createFileFromBitmap() Err 2==> " + e.getLocalizedMessage());
        }
        Log.i("FILE__", "createFileFromBitmap() filePAth==> " + file.getAbsolutePath());
        return file.getAbsolutePath();
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

    private boolean isWriteReadPermission() {
        int n = ContextCompat.checkSelfPermission((Context) this.getApplicationContext(), (String) "android.permission.WRITE_EXTERNAL_STORAGE");
//        int n2 = ContextCompat.checkSelfPermission((Context)this.getApplicationContext(), (String)"android.permission.READ_EXTERNAL_STORAGE");
        int n3 = ContextCompat.checkSelfPermission((Context) this.getApplicationContext(), (String) "android.permission.CAMERA");
        ArrayList arrayList = new ArrayList();
        if (n != 0) {
            arrayList.add((Object) "android.permission.WRITE_EXTERNAL_STORAGE");
        }
//        if (n2 != 0) {
//            arrayList.add((Object)"android.permission.READ_EXTERNAL_STORAGE");
//        }
        if (n3 != 0) {
            arrayList.add((Object) "android.permission.CAMERA");
        }
        if (!arrayList.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) this, (String[]) ((String[]) arrayList.toArray((Object[]) new String[arrayList.size()])), (int) 1236);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int n, String[] arrstring, int[] arrn) {
        super.onRequestPermissionsResult(n, arrstring, arrn);
        int n2 = arrn.length;
        boolean bl = false;
//        boolean bl2 = false;
        boolean bl3 = false;
        if (n2 > 0) {
            for (int i = 0; i < arrstring.length; ++i) {
                if (arrstring[i].equals((Object) "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    if (arrn[i] == 0) {
                        bl = true;
                        continue;
                    }
                    Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Storage permission", (int) 1).show();
                    continue;
                }
                /*if (arrstring[i].equals((Object) "android.permission.READ_EXTERNAL_STORAGE")) {
                    if (arrn[i] == 0) {
                        bl2 = true;
                        continue;
                    }
                    Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Storage permission", (int) 1).show();
                    continue;
                }*/
                if (!arrstring[i].equals((Object) "android.permission.CAMERA")) continue;
                if (arrn[i] == 0) {
                    bl3 = true;
                    continue;
                }
                Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Camera permission", (int) 1).show();
            }
        }
        if (bl && bl3) {
            isPermissionGiven = true;
        } else {
            isPermissionGiven = false;
        }
    }
}