package com.smarthome.iot.data.source.remote.service;

import android.content.Context;

import com.smarthome.iot.data.source.remote.api.ApiAuth;
import com.smarthome.iot.data.source.remote.api.ApiGroup;
import com.smarthome.iot.data.source.remote.api.ApiPosition;
import com.smarthome.iot.utils.AppConstants;

public class AppServiceClient extends ServiceClient {

    private static ApiAuth mApiAuth;
    private static ApiGroup mIApiGroup;
    private static ApiPosition mApiPosition;

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
}
