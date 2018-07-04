package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.DeviceConfigDataSource;
import com.smarthome.iot.data.source.remote.api.ApiDeviceConfig;
import com.smarthome.iot.data.source.remote.response.DeviceConfigResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class DeviceConfigRemoteDataSource implements DeviceConfigDataSource.RemoteDataSource {

    private static DeviceConfigRemoteDataSource instance;

    private ApiDeviceConfig mApiDeviceConfig;

    private static Context mContext;

    public DeviceConfigRemoteDataSource(ApiDeviceConfig apiDeviceConfig){
        this.mApiDeviceConfig = apiDeviceConfig;
    }

    public static synchronized DeviceConfigRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new DeviceConfigRemoteDataSource(AppServiceClient.getDeviceConfigRemoteInstance(context));
        }
        return instance;
    }
    @Override
    public Single<DeviceConfigResponse> allDeviceConfig() {
        return mApiDeviceConfig.allDeviceConfig(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(mContext).getApiAccessToken())
                ,"false");
    }
}
