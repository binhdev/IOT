package com.smarthome.iot.ui.alarm;

import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

public interface EditAlarmContract {

    interface View extends BaseView {
        void editAlarmSuccess();
        void editAlarmFailed();
        void loadDetailAlarmSuccess(DetailAlarmResponse detailAlarmResponse);
        void loadDetailAlarmFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void detailAlarm(int alarmId);
        void editAlarm(int alarmId, AddAlarmRequest addAlarmRequest);
    }

}
