package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.device.ListDeviceResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDevice {

    @PUT("device/edit/{id}")
    Observable editDevice(@Path("id") String id, @Query("name") String name, @Query("position_id") String position_id);

    @DELETE("device/delete")
    Observable deleteGroup(@Query("id") String id);

    @GET("device/detail/{id}")
    Observable deviceDetail(@Path("id") String id);

    @GET("device/list")
    Single<ListDeviceResponse> deviceList(@Header("Token") String token, @Query("pagination") String pagination);

    @POST("device/add")
    Observable addDevice(@Query("name") String name, @Query("code") String code, @Query("position_id") String position_id);

}
