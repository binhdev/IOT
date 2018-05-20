package com.smarthome.iot.data.source.remote.service;

import android.content.Context;

import com.smarthome.iot.data.source.remote.api.IApiAuth;
import com.smarthome.iot.data.source.remote.api.IApiGroup;
import com.smarthome.iot.utils.AppConstants;

public class AppServiceClient extends ServiceClient {

    private static IApiAuth mApiAuth;
    private static IApiGroup mIApiGroup;

    public static IApiAuth getLoginRemoteInstance(Context context) {
        if(mApiAuth == null){
            mApiAuth = createService(context, AppConstants.END_POINT, IApiAuth.class);
        }
        return mApiAuth;
    }

    public static IApiGroup getGroupRemoteInstance(Context context) {
        if(mIApiGroup == null){
            mIApiGroup = createService(context, AppConstants.END_POINT, IApiGroup.class);
        }
        return mIApiGroup;
    }
}
