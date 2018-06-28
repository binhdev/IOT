package com.smarthome.iot.data.source;

import com.smarthome.iot.data.source.remote.response.DeviceConfigResponse;

import io.reactivex.Single;

public interface DeviceConfigDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{
        Single<DeviceConfigResponse> allDeviceConfig();
    }
}
