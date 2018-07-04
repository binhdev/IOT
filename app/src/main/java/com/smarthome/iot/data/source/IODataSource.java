package com.smarthome.iot.data.source;

import com.smarthome.iot.data.source.remote.response.IOResponse;

import io.reactivex.Single;

public interface IODataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{

        Single<IOResponse> ioByDevice(int deviceId, int type);
        Single<IOResponse> ioByDeviceWithDataTypeIO(int deviceId, int type, int dataTypeIO);
    }
}
