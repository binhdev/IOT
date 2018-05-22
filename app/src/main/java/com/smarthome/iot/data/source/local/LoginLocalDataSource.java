package com.smarthome.iot.data.source.local;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.LoginDataSource;

import io.reactivex.Observable;

public class LoginLocalDataSource implements LoginDataSource.LocalDataSource {

    private static LoginLocalDataSource instance;

    public LoginLocalDataSource(){
    }

    public static synchronized LoginLocalDataSource getInstance() {
        if (instance == null) {
            instance = new LoginLocalDataSource();
        }
        return instance;
    }

    @Override
    public Observable insertUser(User user) {
        return null;
    }

    @Override
    public Observable updateUser(User user) {
        return null;
    }

    @Override
    public Observable deleteUser(User user) {
        return null;
    }
}
