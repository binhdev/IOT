package com.smarthome.iot.data.source.local;

import com.smarthome.iot.data.source.DeviceConfigDataSource;

public class DeviceConfigLocalDataSource implements DeviceConfigDataSource.LocalDataSource {
    private static DeviceConfigLocalDataSource instance;

    public DeviceConfigLocalDataSource(){
    }

    public static synchronized DeviceConfigLocalDataSource getInstance() {
        if (instance == null) {
            instance = new DeviceConfigLocalDataSource();
        }
        return instance;
    }
}
