package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.source.DeviceDataSource;
import com.smarthome.iot.data.source.local.DeviceLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.device.DeviceResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class DeviceRepository implements DeviceDataSource.LocalDataSource, DeviceDataSource.RemoteDataSource {
    private static DeviceRepository instance;

    @NonNull
    private DeviceLocalDataSource mDeviceLocalDataSource;

    @NonNull
    private DeviceRemoteDataSource mDeviceRemoteDataSource;

    private DeviceRepository(@NonNull DeviceLocalDataSource deviceLocalDataSource, @NonNull DeviceRemoteDataSource deviceRemoteDataSource){
        mDeviceLocalDataSource = deviceLocalDataSource;
        mDeviceRemoteDataSource = deviceRemoteDataSource;
    }

    public static synchronized DeviceRepository getInstance(@NonNull DeviceLocalDataSource deviceLocalDataSource, @NonNull DeviceRemoteDataSource deviceRemoteDataSource){
        if(instance == null){
            instance = new DeviceRepository(deviceLocalDataSource, deviceRemoteDataSource);
        }
        return instance;
    }
    @Override
    public Single<DeviceResponse> deviceList() {
        return mDeviceRemoteDataSource.deviceList();
    }

    @Override
    public Single<DeviceResponse> deviceByPosition(int positionId) {
        return mDeviceRemoteDataSource.deviceByPosition(positionId);
    }
}
