package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.io.IO;

import java.util.List;

public class IOResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<IO> ioList;

    public List<IO> getIOList(){
        return ioList;
    }

}
