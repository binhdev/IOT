package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.source.IODataSource;
import com.smarthome.iot.data.source.local.IOLocalDataSource;
import com.smarthome.iot.data.source.remote.IORemoteDataSource;
import com.smarthome.iot.data.source.remote.response.IOResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class IORepository implements IODataSource.LocalDataSource, IODataSource.RemoteDataSource {
    private static IORepository instance;

    @NonNull
    private IOLocalDataSource mIOLocalDataSource;

    @NonNull
    private IORemoteDataSource mIORemoteDataSource;

    private IORepository(@NonNull IOLocalDataSource ioLocalDataSource, @NonNull IORemoteDataSource ioRemoteDataSource){
        mIOLocalDataSource = ioLocalDataSource;
        mIORemoteDataSource = ioRemoteDataSource;
    }

    public static synchronized IORepository getInstance(@NonNull IOLocalDataSource ioLocalDataSource, @NonNull IORemoteDataSource ioRemoteDataSource){
        if(instance == null){
            instance = new IORepository(ioLocalDataSource, ioRemoteDataSource);
        }
        return instance;
    }

    @Override
    public Single<IOResponse> ioByDevice(int deviceId, int type) {
        return mIORemoteDataSource.ioByDevice(deviceId, type);
    }

    @Override
    public Single<IOResponse> ioByDeviceWithDataTypeIO(int deviceId, int type, int dataTypeIO) {
        return mIORemoteDataSource.ioByDeviceWithDataTypeIO(deviceId, type, dataTypeIO);
    }
}
