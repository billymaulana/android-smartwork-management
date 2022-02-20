package com.smartworks.smartwork.base.config.response.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartworks.smartwork.base.config.response.GeneralResponse;

public class TokenResponse extends GeneralResponse {

    @SerializedName("result")
    @Expose
    private DataTokenRespon dataTokenRespon;

    public DataTokenRespon getDataTokenRespon() {
        return dataTokenRespon;
    }
}
