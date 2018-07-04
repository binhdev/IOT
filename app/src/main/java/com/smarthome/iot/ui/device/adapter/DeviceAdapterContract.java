package com.smarthome.iot.ui.device.adapter;

import com.smarthome.iot.data.model.device.Device;

public interface DeviceAdapterContract {

    interface Presenter{
        void editDevice(Device device);
        void deleteDevice(int [] arrayId);
    }

    interface View{
        void addDeviceSuccess();
        void addDeviceFailed();
        void editDeviceSuccess();
        void editDeviceFailed();
        void deleteDeviceSuccess();
        void deleteDeviceFailed();
    }
}
