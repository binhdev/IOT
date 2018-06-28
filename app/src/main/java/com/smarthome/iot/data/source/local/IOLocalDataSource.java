package com.smarthome.iot.data.source.local;
import com.smarthome.iot.data.source.IODataSource;

public class IOLocalDataSource implements IODataSource.LocalDataSource {

    private static IOLocalDataSource instance;

    public IOLocalDataSource(){
    }

    public static synchronized IOLocalDataSource getInstance() {
        if (instance == null) {
            instance = new IOLocalDataSource();
        }
        return instance;
    }

}
