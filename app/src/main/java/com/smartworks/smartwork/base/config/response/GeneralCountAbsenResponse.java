package com.smartworks.smartwork.base.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralCountAbsenResponse {
    @SerializedName("code")
    @Expose
    private String Code;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("status")
    @Expose
    private String status;

    public String getCode() {
        return Code;
    }

    public Integer getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }
}
