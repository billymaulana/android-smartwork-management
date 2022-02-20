package com.smartworks.smartwork.base.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralCountResponse {
    @SerializedName("code")
    @Expose
    private String Code;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("count")
    @Expose
    private Integer count;

    public String getCode() {
        return Code;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCount() {
        return count;
    }
}
