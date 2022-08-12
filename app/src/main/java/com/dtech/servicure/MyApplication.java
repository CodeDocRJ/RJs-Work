package com.dtech.servicure;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    public static final Companion Companion = new Companion();
    public static MyApplication instance;

    public MyApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static final class Companion {
        public Companion() {
        }

        public Context getAppContext() {
            if (MyApplication.instance == null) {
                MyApplication.instance = new MyApplication();
            }
            return MyApplication.instance;
        }
    }

}
