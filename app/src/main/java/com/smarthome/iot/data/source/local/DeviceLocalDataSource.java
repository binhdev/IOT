package com.smarthome.iot.data.source.local;

import com.smarthome.iot.data.source.DeviceDataSource;

public class DeviceLocalDataSource implements DeviceDataSource.LocalDataSource {
    private static DeviceLocalDataSource instance;

    public DeviceLocalDataSource(){
    }

    public static synchronized DeviceLocalDataSource getInstance() {
        if (instance == null) {
            instance = new DeviceLocalDataSource();
        }
        return instance;
    }
}
