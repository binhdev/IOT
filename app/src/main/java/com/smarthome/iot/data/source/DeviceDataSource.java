package com.smarthome.iot.data.source;

import com.smarthome.iot.data.source.remote.response.device.ListDeviceResponse;

import io.reactivex.Single;

public interface DeviceDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{

        Single<ListDeviceResponse> deviceList();
    }
}
