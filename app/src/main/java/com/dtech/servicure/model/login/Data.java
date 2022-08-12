package com.dtech.servicure.model.login;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    int id;

    @SerializedName("username")
    String username;

    @SerializedName("name")
    String name;

    @SerializedName("mobile_number")
    String mobileNumber;

    @SerializedName("biker_password")
    String bikerPassword;

    @SerializedName("image")
    String image;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setBikerPassword(String bikerPassword) {
        this.bikerPassword = bikerPassword;
    }
    public String getBikerPassword() {
        return bikerPassword;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

}
