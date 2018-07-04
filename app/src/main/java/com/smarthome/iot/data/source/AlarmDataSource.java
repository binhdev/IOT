package com.smarthome.iot.data.source;

import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;

import io.reactivex.Single;

public interface AlarmDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{
        Single<AlarmResponse> allAlarm();
        Single<DetailAlarmResponse> detailAlarm(int alarmId);
        Single<BaseResponse> addAlarm(AddAlarmRequest addAlarmRequest);
        Single<BaseResponse> editAlarm(int alarmId, AddAlarmRequest addAlarmRequest);
        Single<BaseResponse> deleteAlarm(int[] arrayId);
    }
}
