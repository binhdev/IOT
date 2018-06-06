package com.smarthome.iot.ui.main.fragment.alldevice;

import android.content.Context;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.source.remote.response.device.DeviceResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class AllDevicePresenter  implements AllDeviceContract.Presenter {
    private AllDeviceContract.View mView;
    private DeviceRepository mDeviceRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public AllDevicePresenter(Context context, DeviceRepository deviceRepository, BaseSchedulerProvider schedulerProvider){
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

    @Override
    public void addDevice(Device device) {

    }

    private void handleDeviceListSuccess(DeviceResponse deviceListResponse){
        mView.setDeviceResponseList(deviceListResponse.getDeviceList());
        mView.hideLoadingIndicator();
    }

    private void handleDeviceListFailed(Throwable err){
        Log.e("Falied device", err.getMessage());
    }

    @Override
    public void setView(AllDeviceContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
