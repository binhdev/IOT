package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.LoginDataSource;
import com.smarthome.iot.data.source.remote.api.ApiAuth;
import com.smarthome.iot.data.source.remote.response.auth.LoginResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class LoginRemoteDataSource implements LoginDataSource.RemoteDataSource {

    private static LoginRemoteDataSource instance;

    private ApiAuth mApiAuth;

    public LoginRemoteDataSource(ApiAuth apiAuth){
        this.mApiAuth = apiAuth;
    }

    public static synchronized LoginRemoteDataSource getInstance(Context context) {
        if(instance == null){
            instance = new LoginRemoteDataSource(AppServiceClient.getLoginRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<LoginResponse> doLogin(String email, String password) {
        return mApiAuth.login(email, password);
    }
}
