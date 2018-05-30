package com.smarthome.iot.data.source;

import com.smarthome.iot.data.source.remote.response.device.DeviceResponse;

import io.reactivex.Single;

public interface DeviceDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{

        Single<DeviceResponse> deviceList();

        Single<DeviceResponse> deviceByPosition(int positionId);
    }
}
