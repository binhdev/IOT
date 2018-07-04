package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.AlarmDataSource;
import com.smarthome.iot.data.source.remote.api.ApiAlarm;
import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class AlarmRemoteDataSource implements AlarmDataSource.RemoteDataSource {

    private static AlarmRemoteDataSource instance;

    private ApiAlarm mApiAlarm;

    private static Context mContext;

    public AlarmRemoteDataSource(ApiAlarm apiAlarm){
        this.mApiAlarm = apiAlarm;
    }

    public static synchronized AlarmRemoteDataSource getInstance(Context context) {
        mContext = context;
        if(instance == null){
            instance = new AlarmRemoteDataSource(AppServiceClient.getAlarmRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<AlarmResponse> allAlarm() {
        return mApiAlarm.allAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(mContext).getApiAccessToken()),
                "false");
    }

    @Override
    public Single<DetailAlarmResponse> detailAlarm(int alarmId) {
        return mApiAlarm.detailAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(mContext).getApiAccessToken()), alarmId);
    }

    @Override
    public Single<BaseResponse> deleteAlarm(int[] arrayId) {
        return mApiAlarm.deleteAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(mContext).getApiAccessToken()), arrayId);
    }

    @Override
    public Single<BaseResponse> addAlarm(AddAlarmRequest addAlarmRequest) {
        return mApiAlarm.addAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,
                AppPrefs.getInstance(mContext).getApiAccessToken()), addAlarmRequest);
    }

    @Override
    public Single<BaseResponse> editAlarm(int alarmId, AddAlarmRequest addAlarmRequest) {
        return mApiAlarm.editAlarm(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,
                AppPrefs.getInstance(mContext).getApiAccessToken()), alarmId, addAlarmRequest);
    }
}
