package com.dtech.servicure.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.czt.mp3recorder.MP3Recorder;
import com.dtech.servicure.R;
import com.dtech.servicure.databinding.ActivityPendingProcessBinding;
import com.dtech.servicure.utils.MyFileUtils;
import com.piterwilson.audio.MP3RadioStreamDelegate;
import com.piterwilson.audio.MP3RadioStreamPlayer;
import com.shuyu.waveview.FileUtils;
import com.trafi.anchorbottomsheetbehavior.AnchorBottomSheetBehavior;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PendingProcessActivity extends AppCompatActivity implements MP3RadioStreamDelegate {

    private static final int CAMERA_REQUEST_1 = 101;
    private static final int CAMERA_REQUEST_2 = 202;
    private static final int CAMERA_REQUEST_3 = 303;
    private static final int CAMERA_REQUEST_4 = 404;
    private static final int CAMERA_REQUEST_5 = 505;
    private static final int CAMERA_REQUEST_6 = 606;
    private static final int CODE_TAKE_SIGN = 1001;
    private static final int CODE_WRITE_READ = 111;
    private static final int CODE_WRITE_RECORD = 222;
    private ActivityPendingProcessBinding binding;
    private PendingProcessActivity activity;
    AnchorBottomSheetBehavior<FrameLayout> behaviour;
    private int displayHeight;
    boolean isPermissionGiven = false;
    String filePath1 = "", filePath2 = "", filePath3 = "", filePath4 = "", filePath5 = "", filePath6 = "";
    /**/
    private String filePath;
    MP3Recorder mRecorder;
    boolean mIsRecord = false;
    boolean isHasFile = false;
    MP3RadioStreamPlayer player;
    private boolean isPlaying;

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

        /**/
        binding.incProcess.imgDeleteRecorded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null && new File(filePath).exists()) {
                    boolean isDeleted = new File(filePath).delete();
                    Log.i("AUDIO__", "DELETED ?  " + isDeleted);
                    if (isDeleted) {
                        Toast.makeText(activity, "Audio deleted successfully.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, "Something went wrong while deleting file.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.incProcess.imgRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    player.stop();
                    player.release();
                    player = null;
                    isPlaying = false;
                }
                if (mIsRecord) {
                    resolveStopRecord();
                } else {
                    if (isWriteRecordPermission()) {
                        resolveRecord();
                    }
                }
            }
        });
        binding.incProcess.imgPlayRecorded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHasFile) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            play();
                        }
                    }, 500);
                } else {
                    Toast.makeText(activity, "You have to record first!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void resolveRecord() {
        /*filePath = FileUtils.getAppPath();*/
        /*filePath = MyFileUtils.getAudioPath(activity);
        Log.i("AUDIO__", "resolveRecord() " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Toast.makeText(activity, "Failed to create file", Toast.LENGTH_SHORT).show();
                return;
            }
        }*/

        int offset = dip2px(activity, 1);
//        filePath = FileUtils.getAppPath() + UUID.randomUUID().toString() + ".mp3";
        filePath = MyFileUtils.getAudioPath(activity);
        Log.i("AUDIO__", "resolveRecord() " + filePath);
        mRecorder = new MP3Recorder(new File(filePath));
        int size = getScreenWidth(activity) / offset;//控件默认的间隔是1
        mRecorder.setDataList(binding.incProcess.audioWave.getRecList(), size);

        //Advanced usage
        //int size = (getScreenWidth(getActivity()) / 2) / dip2px(getActivity(), 1);
        //mRecorder.setWaveSpeed(600);
        //mRecorder.setDataList(audioWave.getRecList(), size);
        //audioWave.setDrawStartOffset((getScreenWidth(getActivity()) / 2));
        //audioWave.setDrawReverse(true);
        //audioWave.setDataReverse(true);

        //custom paint
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.color_blue));
        paint.setStrokeWidth(4);
        binding.incProcess.audioWave.setLinePaint(paint);
        binding.incProcess.audioWave.setOffset(offset);

        mRecorder.setErrorHandler(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MP3Recorder.ERROR_TYPE) {
                    Toast.makeText(activity, "No microphone permission", Toast.LENGTH_SHORT).show();
                    resolveError();
                }
            }
        });

        //audioWave.setBaseRecorder(mRecorder);

        try {
            mRecorder.start();
            binding.incProcess.audioWave.startView();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("AUDIO__", "resolveRecord() Err==> " + e.getLocalizedMessage());
            Toast.makeText(activity, "Recording is abnormal", Toast.LENGTH_SHORT).show();
            resolveError();
            return;
        }
        resolveRecordUI();
        mIsRecord = true;
    }

    private void resolveStopRecord() {
        resolveStopUI();
        if (mRecorder != null && mRecorder.isRecording()) {
            mRecorder.setPause(false);
            mRecorder.stop();
            binding.incProcess.audioWave.stopView();
        }
        isHasFile = true;
        mIsRecord = false;
    }

    private void resolveStopUI() {
        binding.incProcess.imgRecord.setImageResource(R.drawable.ic_rec);
    }

    private void resolveRecordUI() {
        binding.incProcess.imgRecord.setImageResource(R.drawable.ic_camera_place);
    }

    private void resolveNormalUI() {
        binding.incProcess.imgRecord.setImageResource(R.drawable.ic_rec);
    }

    private void resolveError() {
        resolveNormalUI();
        FileUtils.deleteFile(filePath);
        filePath = "";
        if (mRecorder != null && mRecorder.isRecording()) {
            mRecorder.stop();
            binding.incProcess.audioWave.stopView();
        }
    }

    public static int dip2px(Context context, float dipValue) {
        float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        return outMetrics.widthPixels;
    }

    private void play() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        player = new MP3RadioStreamPlayer();
        //player.setUrlString(this, true, "http://www.stephaniequinn.com/Music/Commercial%20DEMO%20-%2005.mp3");
        player.setUrlString(filePath);
        player.setDelegate(this);

        int size = getScreenWidth(this) / dip2px(this, 1);//控件默认的间隔是1
        player.setDataList(binding.incProcess.audioWave.getRecList(), size);
        //player.setWaveSpeed(1100);
        //mRecorder.setDataList(audioWave.getRecList(), size);
        //player.setStartWaveTime(5000);
        //audioWave.setDrawBase(false);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.color_blue));
        paint.setStrokeWidth(4);
        binding.incProcess.audioWave.setLinePaint(paint);

        binding.incProcess.audioWave.setBaseRecorder(player);
        binding.incProcess.audioWave.startView();
//        binding.incProcess.audioWave.setWaveColor(getResources().getColor(R.color.color_blue));
        try {
            player.play();
            isPlaying = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            ActivityCompat.requestPermissions((Activity) this, (String[]) ((String[]) arrayList.toArray((Object[]) new String[arrayList.size()])), CODE_WRITE_READ);
            return false;
        }
        return true;
    }

    private boolean isWriteRecordPermission() {
        int n = ContextCompat.checkSelfPermission((Context) this.getApplicationContext(), (String) "android.permission.WRITE_EXTERNAL_STORAGE");
        int n3 = ContextCompat.checkSelfPermission((Context) this.getApplicationContext(), (String) "android.permission.RECORD_AUDIO");
        ArrayList arrayList = new ArrayList();
        if (n != 0) {
            arrayList.add((Object) "android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (n3 != 0) {
            arrayList.add((Object) "android.permission.RECORD_AUDIO");
        }
        if (!arrayList.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) this, (String[]) ((String[]) arrayList.toArray((Object[]) new String[arrayList.size()])), CODE_WRITE_RECORD);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_WRITE_READ) {
            boolean isWrite = false, isCamera = false;
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; ++i) {
                    if (permissions[i].equals((Object) "android.permission.WRITE_EXTERNAL_STORAGE")) {
                        if (grantResults[i] == 0) {
                            isWrite = true;
                            continue;
                        }
                        Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Storage permission", (int) 1).show();
                        continue;
                    }
                    if (!permissions[i].equals((Object) "android.permission.CAMERA")) continue;
                    if (grantResults[i] == 0) {
                        isCamera = true;
                        continue;
                    }
                    Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Camera permission", (int) 1).show();
                }
            }
            if (isWrite && isCamera) {
                isPermissionGiven = true;
            } else {
                isPermissionGiven = false;
            }
        } else if (requestCode == CODE_WRITE_RECORD) {
            boolean isWrite = false, isRecord = false;
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; ++i) {
                    if (permissions[i].equals((Object) "android.permission.WRITE_EXTERNAL_STORAGE")) {
                        if (grantResults[i] == 0) {
                            isWrite = true;
                            continue;
                        }
                        Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Storage permission", (int) 1).show();
                        continue;
                    }
                    if (!permissions[i].equals((Object) "android.permission.RECORD_AUDIO"))
                        continue;
                    if (grantResults[i] == 0) {
                        isRecord = true;
                        continue;
                    }
                    Toast.makeText((Context) this.getApplicationContext(), (CharSequence) "Please allow Recording permission", (int) 1).show();
                }
            }
            if (isWrite && isRecord) {
                resolveRecord();
            }
        }
    }

    @Override
    public void onRadioPlayerPlaybackStarted(MP3RadioStreamPlayer player) {

    }

    @Override
    public void onRadioPlayerStopped(MP3RadioStreamPlayer player) {

    }

    @Override
    public void onRadioPlayerError(MP3RadioStreamPlayer player) {

    }

    @Override
    public void onRadioPlayerBuffering(MP3RadioStreamPlayer player) {

    }
}