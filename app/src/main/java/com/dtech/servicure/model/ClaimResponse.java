package com.dtech.servicure.model;

import com.google.gson.annotations.SerializedName;

public class ClaimResponse {

    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}
