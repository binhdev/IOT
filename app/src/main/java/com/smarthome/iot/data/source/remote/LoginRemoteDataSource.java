package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.ILoginDataSource;
import com.smarthome.iot.data.source.remote.api.IApiAuth;
import com.smarthome.iot.data.source.remote.response.LoginResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LoginRemoteDataSource implements ILoginDataSource.RemoteDataSource {

    private static LoginRemoteDataSource instance;

    private IApiAuth mApiAuth;

    public LoginRemoteDataSource(IApiAuth apiAuth){
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
