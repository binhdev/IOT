package com.smarthome.iot.ui.main.fragment.alarm;

import com.smarthome.iot.data.model.Alarm;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface AlarmContract {
    interface View extends BaseView {
        void setAlarmToView(List<Alarm> alarmList);
    }

    interface Presenter extends BasePresenter<View> {
        void allAlarm();
    }
}
