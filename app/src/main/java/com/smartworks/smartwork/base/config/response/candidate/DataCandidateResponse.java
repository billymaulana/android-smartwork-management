package com.smartworks.smartwork.base.config.response.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCandidateResponse {

    @SerializedName("id")
    @Expose
    private  String id;

    @SerializedName("user")
    @Expose
    private  String user;

    @SerializedName("nama")
    @Expose
    private  String nama;

    @SerializedName("jenis_kelamin")
    @Expose
    private  String jenis_kelamin;

    @SerializedName("birthdate")
    @Expose
    private  String birthdate;

    @SerializedName("email")
    @Expose
    private  String email;

    @SerializedName("phone")
    @Expose
    private  String phone;

    @SerializedName("address")
    @Expose
    private  String address;

    @SerializedName("files")
    @Expose
    private  String files;

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getFiles() {
        return files;
    }
}

