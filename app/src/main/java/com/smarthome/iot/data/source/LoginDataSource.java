package com.smarthome.iot.data.source;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.remote.response.auth.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface LoginDataSource {
    /**
     * Local data for login
     */

    interface LocalDataSource{
        Observable insertUser(@NonNull User user);

        Observable updateUser(@NonNull User user);

        Observable deleteUser(@NonNull User user);
    }

    /**
     * Remote data for login
     */
    interface RemoteDataSource{
        Single<LoginResponse> doLogin(String email, String password);
    }
}
