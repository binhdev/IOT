package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiAuth {

    @POST("auth/register")
    Observable register(String email, String password);

    @GET("auth/active_email/{token}")
    Observable activeEmail(@Path("token") String token);

    @POST("auth/send_mail_active/{token}")
    Observable activeMailActive(@Path("token") String token);

    @POST("auth/login")
    Single<LoginResponse> login(@Query("login") String login, @Query("password") String password);

    @POST("auth/logout")
    Observable logout();

    @POST("auth/forgot_password")
    Observable forgotPassword();

    @POST("auth/reset_password/{token}")
    Observable resetPassword(@Path("token") String token, String email, String password, String password_confirmation);
}
