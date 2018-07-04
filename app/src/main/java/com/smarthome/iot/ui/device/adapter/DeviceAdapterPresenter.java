package com.smarthome.iot.ui.device.adapter;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class DeviceAdapterPresenter implements DeviceAdapterContract.Presenter {

    private DeviceAdapterContract.View mView;
    private DeviceRepository mDeviceRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public DeviceAdapterPresenter(Context context, DeviceRepository deviceRepository, BaseSchedulerProvider schedulerProvider,
                                  DeviceAdapterContract.View view){
        this.context = Preconditions.checkNotNull(context);
        mDeviceRepository = Preconditions.checkNotNull(deviceRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
        this.mView = view;
    }

    @Override
    public void editDevice(Device device) {
        mDeviceRepository.editDevice(device)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleEditDeviceSuccess(),
                        error -> handleEditDeviceFailed());
    }

    @Override
    public void deleteDevice(int[] arrayId) {
        mDeviceRepository.deleteDevice(arrayId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleDeleteDeviceSuccess(),
                        error -> handleDeleteDeviceFailed());
    }

    private void handleEditDeviceSuccess(){
        mView.editDeviceSuccess();
    }

    private void handleEditDeviceFailed(){
        mView.editDeviceFailed();
    }

    private void handleDeleteDeviceSuccess(){
        mView.deleteDeviceSuccess();
    }

    private void handleDeleteDeviceFailed(){
        mView.deleteDeviceFailed();
    }
}
