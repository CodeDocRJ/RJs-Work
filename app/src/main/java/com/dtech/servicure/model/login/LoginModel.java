package com.dtech.servicure.model.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModel {

    @SerializedName("success")
    boolean success;

    @SerializedName("data")
    List<Data> data;

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

}
