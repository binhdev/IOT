package com.smarthome.iot.ui.device;

import com.smarthome.iot.data.source.remote.response.device.ListDeviceResponse;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface DeviceContract {

    interface View extends BaseView {
        void setDeviceResponseList(List<ListDeviceResponse.Data> deviceResponseList);
    }

    interface Presenter extends BasePresenter<DeviceContract.View> {
        void deviceList(String pagination);
    }

}
