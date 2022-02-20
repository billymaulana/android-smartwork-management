package com.smartworks.smartwork.base.config.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class RequestApply {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("jk")
    @Expose
    private String jk;

    @SerializedName("birthdate")
    @Expose
    private String birthdate;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("cv")
    @Expose
    private String cv;

    public RequestApply(String token, String nama, String jk, String birthdate, String email, String phone, String address, String cv) {
        this.token = token;
        this.nama = nama;
        this.jk = jk;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cv = cv;
    }
}
