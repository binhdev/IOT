package com.smarthome.iot.data.source.remote.service;

import android.content.Context;

import com.smarthome.iot.data.source.remote.api.IApiAuth;
import com.smarthome.iot.utils.AppConstants;

public class AppServiceClient extends ServiceClient {

    private static IApiAuth mApiAuth;

    public static IApiAuth getLoginRemoteInstance(Context context) {
        if(mApiAuth == null){
            mApiAuth = createService(context, AppConstants.END_POINT, IApiAuth.class);
        }
        return mApiAuth;
    }
}
