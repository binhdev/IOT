package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

import java.util.List;

public class PositionResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Position> positionList;

    public List<Position> getPositionList(){
        return positionList;
    }

}
