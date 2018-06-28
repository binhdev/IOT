package com.smarthome.iot.ui.main.fragment.alldevice;

import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface AllDeviceContract {
    interface View extends BaseView {
        void setDeviceResponseList(List<Device> deviceList);
    }

    interface Presenter extends BasePresenter<AllDeviceContract.View> {
        void deviceList(String pagination);
        void addDevice(Device device);
    }
}
