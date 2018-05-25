package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.DeviceDataSource;
import com.smarthome.iot.data.source.remote.api.ApiDevice;
import com.smarthome.iot.data.source.remote.response.device.ListDeviceResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppPrefs;

import io.reactivex.Single;

public class DeviceRemoteDataSource implements DeviceDataSource.RemoteDataSource {

    private static DeviceRemoteDataSource instance;

    private ApiDevice mApiDevice;

    private Context context;

    public DeviceRemoteDataSource(ApiDevice mApiDevice){
        this.mApiDevice = mApiDevice;
    }

    public static synchronized DeviceRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new DeviceRemoteDataSource(AppServiceClient.getDeviceRemoteInstance(context));
        }
        return instance;
    }
    @Override
    public Single<ListDeviceResponse> deviceList() {
        return mApiDevice.deviceList(AppPrefs.getInstance(context).getApiToken(),"false");
    }
}
