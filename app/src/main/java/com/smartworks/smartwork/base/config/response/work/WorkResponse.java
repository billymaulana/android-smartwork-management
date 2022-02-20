package com.smartworks.smartwork.base.config.response.work;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartworks.smartwork.base.config.response.GeneralResponse;

import java.util.List;

public class WorkResponse extends GeneralResponse {

    @SerializedName("result")
    @Expose
    private List<DataWorkResponse> dataWorkResponses;

    public List<DataWorkResponse> getDataWorkResponses() {
        return dataWorkResponses;
    }
}
