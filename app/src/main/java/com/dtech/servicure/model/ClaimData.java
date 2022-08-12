package com.dtech.servicure.model;

import com.google.gson.annotations.SerializedName;

public class ClaimData {

    @SerializedName("biker_id")
    private String bikerId;
    @SerializedName("claim_id")
    private String claimId;
    @SerializedName("note")
    private String note;

    public ClaimData(String bikerId, String claimId, String note) {
        this.bikerId = bikerId;
        this.claimId = claimId;
        this.note = note;
    }

    public String getBikerId() {
        return bikerId;
    }

    public void setBikerId(String bikerId) {
        this.bikerId = bikerId;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
