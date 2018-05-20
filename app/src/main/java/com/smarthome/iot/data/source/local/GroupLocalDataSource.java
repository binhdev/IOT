package com.smarthome.iot.data.source.local;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.IGroupDataSource;

import io.reactivex.Observable;

public class GroupLocalDataSource implements IGroupDataSource.LocalDataSource {

    private static GroupLocalDataSource instance;

    public GroupLocalDataSource(){
    }

    public static synchronized GroupLocalDataSource getInstance() {
        if (instance == null) {
            instance = new GroupLocalDataSource();
        }
        return instance;
    }


    @Override
    public Observable insertGroup(User user) {
        return null;
    }

    @Override
    public Observable deleteGroup(User user) {
        return null;
    }
}
