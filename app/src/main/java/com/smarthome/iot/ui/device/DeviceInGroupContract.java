package com.smarthome.iot.ui.device;

import com.smarthome.iot.data.source.remote.response.DeviceInGroupResponse;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface DeviceInGroupContract {
    interface View extends BaseView {
        void setDeviceToView(List<DeviceInGroupResponse.Data> deviceResponseList);
    }

    interface Presenter extends BasePresenter<DeviceInPositionContract.View> {
        void deviceInGroup(String pagination, int positionId);
    }
}
