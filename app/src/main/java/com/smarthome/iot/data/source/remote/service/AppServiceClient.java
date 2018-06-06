package com.smarthome.iot.data.source.remote.service;

import android.content.Context;

import com.smarthome.iot.data.source.remote.api.ApiAuth;
import com.smarthome.iot.data.source.remote.api.ApiDevice;
import com.smarthome.iot.data.source.remote.api.ApiDeviceConfig;
import com.smarthome.iot.data.source.remote.api.ApiGroup;
import com.smarthome.iot.data.source.remote.api.ApiPosition;
import com.smarthome.iot.utils.AppConstants;

public class AppServiceClient extends ServiceClient {

    private static ApiAuth mApiAuth;
    private static ApiGroup mIApiGroup;
    private static ApiPosition mApiPosition;
    private static ApiDevice mApiDevice;
    private static ApiDeviceConfig mApiDeviceConfig;

    public static ApiAuth getLoginRemoteInstance(Context context) {
        if(mApiAuth == null){
            mApiAuth = createService(context, AppConstants.END_POINT, ApiAuth.class);
        }
        return mApiAuth;
    }

    public static ApiGroup getGroupRemoteInstance(Context context) {
        if(mIApiGroup == null){
            mIApiGroup = createService(context, AppConstants.END_POINT, ApiGroup.class);
        }
        return mIApiGroup;
    }

    public static ApiPosition getPositionRemoteInstance(Context context) {
        if(mApiPosition == null){
            mApiPosition = createService(context, AppConstants.END_POINT, ApiPosition.class);
        }
        return mApiPosition;
    }

    public static ApiDevice getDeviceRemoteInstance(Context context) {
        if(mApiDevice == null){
            mApiDevice = createService(context, AppConstants.END_POINT, ApiDevice.class);
        }
        return mApiDevice;
    }

    public static ApiDeviceConfig getDeviceConfigRemoteInstance(Context context) {
        if(mApiDeviceConfig == null){
            mApiDeviceConfig = createService(context, AppConstants.END_POINT, ApiDeviceConfig.class);
        }
        return mApiDeviceConfig;
    }
}
