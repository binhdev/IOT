package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.ILoginDataSource;
import com.smarthome.iot.data.source.local.LoginLocalDataSource;
import com.smarthome.iot.data.source.remote.LoginRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class LoginRepository implements ILoginDataSource.LocalDataSource, ILoginDataSource.RemoteDataSource {

    private static LoginRepository instance;

    private LoginRepository(@NonNull LoginLocalDataSource loginLocalDataSource, @NonNull LoginRemoteDataSource loginRemoteDataSource){
        mLoginLocalDataSource = loginLocalDataSource;
        mLoginRemoteDataSource = loginRemoteDataSource;
    }

    public static synchronized LoginRepository getInstance(@NonNull LoginLocalDataSource loginLocalDataSource, @NonNull LoginRemoteDataSource loginRemoteDataSource){
        if(instance == null){
            instance = new LoginRepository(loginLocalDataSource, loginRemoteDataSource);
        }
        return instance;
    }
    @NonNull
    private LoginLocalDataSource mLoginLocalDataSource;

    @NonNull
    private LoginRemoteDataSource mLoginRemoteDataSource;

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

    @Override
    public Single<LoginResponse> doLogin(String email, String password) {
        return mLoginRemoteDataSource.doLogin(email, password);
    }
}
