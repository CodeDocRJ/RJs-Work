package com.dtech.servicure.model.home;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomePickupModel implements Serializable {

    @SerializedName("total_biker_request")
    int totalBikerRequest;

    @SerializedName("biker_request")
    List<BikerRequest> bikerRequest;


    public void setTotalBikerRequest(int totalBikerRequest) {
        this.totalBikerRequest = totalBikerRequest;
    }
    public int getTotalBikerRequest() {
        return totalBikerRequest;
    }

    public void setBikerRequest(List<BikerRequest> bikerRequest) {
        this.bikerRequest = bikerRequest;
    }
    public List<BikerRequest> getBikerRequest() {
        return bikerRequest;
    }
}
