package com.smarthome.iot.data.source.remote.service;

import android.content.Context;

import com.smarthome.iot.data.source.AlarmDataSource;
import com.smarthome.iot.data.source.remote.api.ApiAlarm;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class AlarmRemoteDataSource implements AlarmDataSource.RemoteDataSource {

    private static AlarmRemoteDataSource instance;

    private ApiAlarm mApiAlarm;

    private Context context;

    public AlarmRemoteDataSource(ApiAlarm apiAlarm){
        this.mApiAlarm = apiAlarm;
    }

    public static synchronized AlarmRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new AlarmRemoteDataSource(AppServiceClient.getAlarmRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<AlarmResponse> allAlarm() {
        return mApiAlarm.allAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                "false");
    }

    @Override
    public Single<BaseResponse> deleteAlarm(int[] arrayId) {
        return mApiAlarm.deleteAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()), arrayId);
    }
}