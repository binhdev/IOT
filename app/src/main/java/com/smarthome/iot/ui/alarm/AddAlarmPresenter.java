package com.smarthome.iot.ui.alarm;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.utils.Flash;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class AddAlarmPresenter implements AddAlarmContract.Presenter {

    private AddAlarmContract.View mView;
    private AlarmRepository mAlarmRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;


    public AddAlarmPresenter(Context context, AlarmRepository alarmRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mAlarmRepository = Preconditions.checkNotNull(alarmRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }

    @Override
    public void addAlarm(AddAlarmRequest addAlarmRequest) {
        mView.showLoadingIndicator();
        mAlarmRepository.addAlarm(addAlarmRequest)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui()).subscribe(baseResponse -> handleAddAlarmSuccess(baseResponse),
                error -> handleAddAlarmFailed(error));
    }

    private void handleAddAlarmSuccess(BaseResponse baseResponse){
        mView.hideLoadingIndicator();
        if(baseResponse.getSuccess())
            mView.addAlarmSuccess();
        else {
            Flash.pushError(baseResponse.getMessage());
            mView.addAlarmFailed();
        }
    }

    private void handleAddAlarmFailed(Throwable err){
        mView.hideLoadingIndicator();
        mView.addAlarmFailed();
    }

    @Override
    public void setView(AddAlarmContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
