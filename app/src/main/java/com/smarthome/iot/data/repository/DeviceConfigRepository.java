package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.source.DeviceConfigDataSource;
import com.smarthome.iot.data.source.local.DeviceConfigLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceConfigRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.DeviceConfigResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class DeviceConfigRepository implements DeviceConfigDataSource.LocalDataSource, DeviceConfigDataSource.RemoteDataSource {

    private static DeviceConfigRepository instance;

    @NonNull
    private DeviceConfigLocalDataSource mDeviceConfigLocalDataSource;

    @NonNull
    private DeviceConfigRemoteDataSource mDeviceConfigRemoteDataSource;

    private DeviceConfigRepository(@NonNull DeviceConfigLocalDataSource deviceConfigLocalDataSource
            , @NonNull DeviceConfigRemoteDataSource deviceConfigRemoteDataSource){
        mDeviceConfigLocalDataSource = deviceConfigLocalDataSource;
        mDeviceConfigRemoteDataSource = deviceConfigRemoteDataSource;
    }

    public static synchronized DeviceConfigRepository getInstance(@NonNull DeviceConfigLocalDataSource deviceConfigLocalDataSource
            , @NonNull DeviceConfigRemoteDataSource deviceConfigRemoteDataSource){
        if(instance == null){
            instance = new DeviceConfigRepository(deviceConfigLocalDataSource, deviceConfigRemoteDataSource);
        }
        return instance;
    }
    @Override
    public Single<DeviceConfigResponse> allDeviceConfig() {
        return mDeviceConfigRemoteDataSource.allDeviceConfig();
    }
}
