package com.smarthome.iot.ui.main.fragment.alarm;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class AlarmPresenter implements AlarmContract.Presenter {

    private AlarmContract.View mView;
    private AlarmRepository mAlarmRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context mContext;

    public AlarmPresenter(Context context, AlarmRepository alarmRepository, BaseSchedulerProvider schedulerProvider){
        this.mContext = Preconditions.checkNotNull(context);
        mAlarmRepository = Preconditions.checkNotNull(alarmRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }

    @Override
    public void setView(AlarmContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void allAlarm() {
        mView.showLoadingIndicator();
        mAlarmRepository.allAlarm()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(alarmResponse -> handleAllAlarmSuccess(alarmResponse),
                        error -> handleAllAlarmFailed());
    }

    private void handleAllAlarmSuccess(AlarmResponse alarmResponse){
        mView.setAlarmToView(alarmResponse.getAlarmList());
        mView.hideLoadingIndicator();
    }

    private void handleAllAlarmFailed(){

    }
}
