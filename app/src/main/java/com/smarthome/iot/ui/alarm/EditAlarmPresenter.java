package com.smarthome.iot.ui.alarm;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.utils.Flash;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class EditAlarmPresenter implements EditAlarmContract.Presenter {

    private EditAlarmContract.View mView;
    private AlarmRepository mAlarmRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;


    public EditAlarmPresenter(Context context, AlarmRepository alarmRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mAlarmRepository = Preconditions.checkNotNull(alarmRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }

    @Override
    public void detailAlarm(int alarmId) {
        mView.showLoadingIndicator();
        mAlarmRepository.detailAlarm(alarmId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui()).subscribe(alarmResponse -> handleDetailAlarmSuccess(alarmResponse),
                error -> handleDetailAlarmFailed(error));
    }

    private void handleDetailAlarmSuccess(DetailAlarmResponse detailAlarmResponse){
        mView.hideLoadingIndicator();
        if(detailAlarmResponse.getSuccess())
            mView.loadDetailAlarmSuccess(detailAlarmResponse);
        else {
            Flash.pushError(detailAlarmResponse.getMessage());
            mView.loadDetailAlarmFailed();
        }
    }

    private void handleDetailAlarmFailed(Throwable err){
        mView.hideLoadingIndicator();
        mView.editAlarmFailed();
    }

    @Override
    public void editAlarm(int alarmId, AddAlarmRequest addAlarmRequest) {
        mView.showLoadingIndicator();
        mAlarmRepository.editAlarm(alarmId, addAlarmRequest)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui()).subscribe(baseResponse -> handleEditAlarmSuccess(baseResponse),
                error -> handleEditAlarmFailed(error));
    }

    private void handleEditAlarmSuccess(BaseResponse baseResponse){
        mView.hideLoadingIndicator();
        if(baseResponse.getSuccess())
            mView.editAlarmSuccess();
        else {
            Flash.pushError(baseResponse.getMessage());
            mView.editAlarmFailed();
        }
    }

    private void handleEditAlarmFailed(Throwable err){
        mView.hideLoadingIndicator();
        mView.editAlarmFailed();
    }

    @Override
    public void setView(EditAlarmContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
