package com.dtech.servicure.model;

public class PendingModel {

    private String name;
    private String date;
    private boolean isOpen;

    public PendingModel(String name, String date, boolean isOpen) {
        this.name = name;
        this.date = date;
        this.isOpen = isOpen;
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
