package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.source.AlarmDataSource;
import com.smarthome.iot.data.source.local.AlarmLocalDataSource;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;
import com.smarthome.iot.data.source.remote.service.AlarmRemoteDataSource;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class AlarmRepository implements AlarmDataSource.LocalDataSource, AlarmDataSource.RemoteDataSource  {

    private static AlarmRepository instance;

    @NonNull
    private AlarmLocalDataSource mAlarmLocalDataSource;

    @NonNull
    private AlarmRemoteDataSource mAlarmRemoteDataSource;

    private AlarmRepository(@NonNull AlarmLocalDataSource alarmLocalDataSource, @NonNull AlarmRemoteDataSource alarmRemoteDataSource){
        mAlarmLocalDataSource = alarmLocalDataSource;
        mAlarmRemoteDataSource = alarmRemoteDataSource;
    }

    public static synchronized AlarmRepository getInstance(@NonNull AlarmLocalDataSource alarmLocalDataSource, @NonNull AlarmRemoteDataSource alarmRemoteDataSource){
        if(instance == null){
            instance = new AlarmRepository(alarmLocalDataSource, alarmRemoteDataSource);
        }
        return instance;
    }

    @Override
    public Single<AlarmResponse> allAlarm() {
        return mAlarmRemoteDataSource.allAlarm();
    }

    @Override
    public Single<BaseResponse> deleteAlarm(int[] arrayId) {
        return mAlarmRemoteDataSource.deleteAlarm(arrayId);
    }
}
