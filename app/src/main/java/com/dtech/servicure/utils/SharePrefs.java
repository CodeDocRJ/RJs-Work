package com.dtech.servicure.utils;

import android.content.SharedPreferences;

import com.dtech.servicure.MyApplication;
import com.dtech.servicure.model.login.LoginModel;
import com.google.gson.Gson;

public class SharePrefs {

    private static final String LOGIN_DATA = "LOGIN_DATA";

    private static SharedPreferences get() {
        return MyApplication.Companion.getAppContext().getSharedPreferences("ServiCure", 0);
    }


    public static void setLoginData(LoginModel loginModel) {
        String json = new Gson().toJson(loginModel);
        SharePrefs.get().edit().putString(LOGIN_DATA, json).commit();
    }

    public static LoginModel getLoginData() {
        return new Gson().fromJson(SharePrefs.get().getString(LOGIN_DATA, ""), LoginModel.class);
    }

}
