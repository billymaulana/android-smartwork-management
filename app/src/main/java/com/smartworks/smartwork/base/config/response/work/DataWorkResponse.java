package com.smartworks.smartwork.base.config.response.work;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWorkResponse {


    @SerializedName("id")
    @Expose
    private  String id;

    @SerializedName("nama")
    @Expose
    private  String nama;

    @SerializedName("deskripsi")
    @Expose
    private  String deskripsi;

    @SerializedName("status")
    @Expose
    private  String status;

    @SerializedName("created_at")
    @Expose
    private  String created_at;

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }
}
