package com.smarthome.iot.ui.main.fragment.alarm.adapter;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.model.alarm.Alarm;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class AlarmAdapterPresenter implements AlarmAdapterContract.Presenter {

    private Context mContext;
    private AlarmAdapterContract.Presenter mPresenter;
    private AlarmAdapterContract.View mView;
    private AlarmRepository mAlarmRepository;
    private BaseSchedulerProvider mSchedulerProvider;

    public AlarmAdapterPresenter(Context context, AlarmRepository alarmRepository, BaseSchedulerProvider schedulerProvider,
                        AlarmAdapterContract.View view){
        this.mContext = Preconditions.checkNotNull(context);
        mAlarmRepository = Preconditions.checkNotNull(alarmRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
        this.mView = view;
    }

    @Override
    public void deleteAlarm(int[] arrayId) {
        mAlarmRepository.deleteAlarm(arrayId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(s -> handleDeleteAlarmSuccess(),
                        error -> handleDeleteAlarmFailed());;
    }

    @Override
    public void editAlarm(Alarm alarm) {

    }

    private void handleDeleteAlarmSuccess(){
        mView.deleteAlarmSuccess();
    }

    private void handleDeleteAlarmFailed(){
        mView.deleteAlarmFailed();
    }
}
