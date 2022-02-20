package com.smartworks.smartwork.base.config.response.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTokenRespon {

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("nama")
    @Expose
    private String nama;


    public String getRole() {
        return role;
    }

    public String getNama() {
        return nama;
    }
}
