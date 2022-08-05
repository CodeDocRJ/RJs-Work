package com.dtech.servicure.model;

public class PendingModel {

    private String name;
    private String date;
    private boolean isExpanded;

    public PendingModel(String name, String date, boolean isExpanded) {
        this.name = name;
        this.date = date;
        this.isExpanded = isExpanded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
