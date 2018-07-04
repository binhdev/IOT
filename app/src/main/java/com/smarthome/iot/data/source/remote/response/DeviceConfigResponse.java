package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.device.DeviceConfig;

import java.util.List;

public class DeviceConfigResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<DeviceConfig> deviceConfigList;

    public List<DeviceConfig> getDeviceConfigList() {
        return deviceConfigList;
    }
}
