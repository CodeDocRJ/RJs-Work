package com.dtech.servicure.model.pending;

import com.dtech.servicure.model.home.BikerRequest;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PendingPickupModel implements Serializable {

    @SerializedName("total_biker_pickup_pending")
    int totalBikerPickupPending;

    @SerializedName("biker_pickup_pending")
    List<BikerPickupPending> bikerPickupPending;

    public int getTotalBikerPickupPending() {
        return totalBikerPickupPending;
    }

    public void setTotalBikerPickupPending(int totalBikerPickupPending) {
        this.totalBikerPickupPending = totalBikerPickupPending;
    }

    public List<BikerPickupPending> getBikerPickupPending() {
        return bikerPickupPending;
    }

    public void setBikerPickupPending(List<BikerPickupPending> bikerPickupPending) {
        this.bikerPickupPending = bikerPickupPending;
    }
}
