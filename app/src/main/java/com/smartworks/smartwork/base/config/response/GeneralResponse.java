package com.smartworks.smartwork.base.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralResponse {
    @SerializedName("code")
    @Expose
    private String Code;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    public String getCode() {
        return Code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
