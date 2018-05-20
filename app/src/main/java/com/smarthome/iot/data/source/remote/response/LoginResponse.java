package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Data;

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
