package com.smarthome.iot.data.source.remote.response.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

import java.util.List;

public class DeviceResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Device> deviceList;

    public List<Device> getDeviceList() {
        return deviceList;
    }
}
