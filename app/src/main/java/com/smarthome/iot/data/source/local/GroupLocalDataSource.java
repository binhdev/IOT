package com.smarthome.iot.data.source.local;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.GroupDataSource;

import io.reactivex.Observable;

public class GroupLocalDataSource implements GroupDataSource.LocalDataSource {

    private static GroupLocalDataSource instance;

    public GroupLocalDataSource(){
    }

    public static synchronized GroupLocalDataSource getInstance() {
        if (instance == null) {
            instance = new GroupLocalDataSource();
        }
        return instance;
    }
}
