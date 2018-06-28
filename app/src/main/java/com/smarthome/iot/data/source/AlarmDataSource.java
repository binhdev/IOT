package com.smarthome.iot.data.source;

import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;

import io.reactivex.Single;

public interface AlarmDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{
        Single<AlarmResponse> allAlarm();
        Single<BaseResponse> deleteAlarm(int[] arrayId);
    }
}
