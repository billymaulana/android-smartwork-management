package com.smartworks.smartwork.base.config.response.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartworks.smartwork.base.config.response.GeneralResponse;
import com.smartworks.smartwork.base.config.response.work.DataWorkResponse;

import java.util.List;

public class CandidateResponse extends GeneralResponse {

    @SerializedName("result")
    @Expose
    private List<DataCandidateResponse> dataCandidateResponses;

    public List<DataCandidateResponse> getDataCandidateResponses() {
        return dataCandidateResponses;
    }
}
