package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.alarm.Alarm;

import java.util.List;

public class AlarmResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Alarm> alarmList;

    public List<Alarm> getAlarmList(){
        return alarmList;
    }

}
