package com.smarthome.iot.ui.device;

import android.content.Context;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.source.remote.response.device.ListDeviceResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class DevicePresenter implements DeviceContract.Presenter {
    private DeviceContract.View mView;
    private DeviceRepository mDeviceRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public DevicePresenter(Context context, DeviceRepository deviceRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mDeviceRepository = Preconditions.checkNotNull(deviceRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }

    @Override
    public void deviceList(String pagination) {
        mView.showLoadingIndicator();
        mDeviceRepository.deviceList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(deviceListResponse -> handleDeviceListSuccess(deviceListResponse),
                        error -> handleDeviceListFailed(error));
    }

    private void handleDeviceListSuccess(ListDeviceResponse deviceListResponse){
        mView.setDeviceResponseList(deviceListResponse.getDeviceResponseList());
        mView.hideLoadingIndicator();
    }

    private void handleDeviceListFailed(Throwable err){
        Log.e("Falied device", err.getMessage());
    }

    @Override
    public void setView(DeviceContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
