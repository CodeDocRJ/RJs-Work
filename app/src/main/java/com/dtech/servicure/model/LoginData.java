package com.dtech.servicure.model;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("mobile_number")
    private String mobilenumber;
    @SerializedName("password")
    private String password;

    public LoginData(String email, String password) {
        this.mobilenumber = email;
        this.password = password;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
