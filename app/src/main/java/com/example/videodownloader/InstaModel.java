package com.example.videodownloader;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InstaModel
{
    @SerializedName("info")
    private ArrayList<InstaInfo> info;

    public InstaModel(ArrayList<InstaInfo> info) {
        this.info = info;
    }

    public ArrayList<InstaInfo> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<InstaInfo> info) {
        this.info = info;
    }
}
