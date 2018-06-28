package com.smarthome.iot.data.source.local;
import com.smarthome.iot.data.source.AlarmDataSource;

public class AlarmLocalDataSource implements AlarmDataSource.LocalDataSource {

    private static AlarmLocalDataSource instance;

    public AlarmLocalDataSource(){
    }

    public static synchronized AlarmLocalDataSource getInstance() {
        if (instance == null) {
            instance = new AlarmLocalDataSource();
        }
        return instance;
    }

}
