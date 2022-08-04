package com.dtech.servicure.utils;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFileUtils {

    public static String getDirectory(Context context, String string) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), string);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getImagePath(Context context) {
        String string = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(".jpeg");
        String string2 = stringBuilder.toString();
        return new File(getDirectory(context, "My Collection"), string2).getAbsolutePath();
    }

    private static final void scanMedia(String string, Context context) {
        MediaScannerConnection.scanFile((Context) context, (String[]) new String[]{string}, null, (MediaScannerConnection.OnScanCompletedListener) new MediaScannerConnection.OnScanCompletedListener() {

            public void onScanCompleted(String string, Uri uri) {
            }
        });
    }

    public static File getImageFile(Activity activity) {
        File parent = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(parent, "My Clicks");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
