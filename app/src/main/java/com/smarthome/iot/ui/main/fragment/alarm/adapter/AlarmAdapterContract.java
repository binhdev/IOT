package com.smarthome.iot.ui.main.fragment.alarm.adapter;

import com.smarthome.iot.data.model.Alarm;

public interface AlarmAdapterContract {
    interface Presenter{
        void deleteAlarm(int[] arrayId);
        void editAlarm(Alarm alarm);
    }

    interface View{
        void deleteAlarmSuccess();
        void deleteAlarmFailed();
    }
}
