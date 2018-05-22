package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Position;

import java.util.List;

public class PositionListResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Position> positionList;

    public List<Position> getPositionList(){
        return positionList;
    }
}
