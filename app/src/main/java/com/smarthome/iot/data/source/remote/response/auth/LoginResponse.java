package com.smarthome.iot.data.source.remote.response.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Data;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

public class LoginResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData(){
        return data;
    }

    public void setData(Data data){
        this.data = data;
    }
}
