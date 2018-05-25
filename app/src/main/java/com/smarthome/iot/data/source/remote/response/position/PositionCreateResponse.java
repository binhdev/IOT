package com.smarthome.iot.data.source.remote.response.position;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

import java.util.List;

public class PositionCreateResponse  extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<Object> data;

    public List<Object> getData(){
        return data;
    }
}
