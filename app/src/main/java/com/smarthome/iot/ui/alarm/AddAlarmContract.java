package com.smarthome.iot.ui.alarm;

import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

public interface AddAlarmContract {

    interface View extends BaseView {
        void addAlarmSuccess();
        void addAlarmFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void addAlarm(AddAlarmRequest addAlarmRequest);
    }

}
