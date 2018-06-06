package com.smarthome.iot.ui.device;

import android.content.Context;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.source.remote.response.device.DeviceResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class DeviceInPositionPresenter implements DeviceInPositionContract.Presenter {
    private DeviceInPositionContract.View mView;
    private DeviceRepository mDeviceRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public DeviceInPositionPresenter(Context context, DeviceRepository deviceRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mDeviceRepository = Preconditions.checkNotNull(deviceRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }

    @Override
    public void deviceByPosition(String pagination, int positionId) {
        mView.showLoadingIndicator();
        mDeviceRepository.deviceByPosition(positionId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(deviceListResponse -> handleDeviceListSuccess(deviceListResponse),
                        error -> handleDeviceListFailed(error));
    }

    private void handleDeviceListSuccess(DeviceResponse deviceListResponse){
        if(deviceListResponse.getDeviceList() != null)
            mView.setDeviceToView(deviceListResponse.getDeviceList());
        mView.hideLoadingIndicator();
    }

    private void handleDeviceListFailed(Throwable err){
        Log.e("Falied device", err.getMessage());
    }

    @Override
    public void setView(DeviceInPositionContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
