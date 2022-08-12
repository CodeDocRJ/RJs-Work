package com.dtech.servicure.model.pending;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BikerPickupPending implements Serializable {

    @SerializedName("id")
    int id;
    @SerializedName("status")
    String status;
    @SerializedName("date_time")
    String dateTime;
    @SerializedName("claim_ref_no")
    String claimRefNo;
    @SerializedName("insured_name")
    String insuredName;
    @SerializedName("city")
    String city;
    @SerializedName("address_line_1")
    String addressLine1;
    @SerializedName("address_line_2")
    String addressLine2;
    @SerializedName("mobile_number")
    String mobileNumber;
    @SerializedName("sum_insured")
    String sumInsured;
    @SerializedName("imei_no")
    String imeiNo;

    boolean isExpanded;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getClaimRefNo() {
        return claimRefNo;
    }

    public void setClaimRefNo(String claimRefNo) {
        this.claimRefNo = claimRefNo;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(String sumInsured) {
        this.sumInsured = sumInsured;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
