package com.smarthome.iot.ui.device;

import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface DeviceInPositionContract {

    interface View extends BaseView {
        void setDeviceToView(List<Device> deviceList);
    }

    interface Presenter extends BasePresenter<DeviceInPositionContract.View> {
        void deviceByPosition(String pagination, int positionId);
    }

}
