package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.deviceconfig.DeviceConfigResponse;

import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDeviceConfig {

//    @POST("device_config/add")
//    Single<BaseResponse> addDevice(@Header("Authorization") String token, @Query("name") String name, @Query("code") String code, @Query("position_id") int positionId);
//
//    @PUT("device_config/edit/{id}")
//    Single<BaseResponse> editDevice(@Header("Authorization") String token,@Path("id") int id, @Query("name") String name, @Query("position_id") int position_id);
//
//    @DELETE("device_config/delete")
//    Single<BaseResponse> deleteGroup(@Header("Authorization") String token,@Query("id") String id);
//
//    @GET("device_config/detail/{id}")
//    Single deviceDetail(@Path("id") int id);

    @GET("device_config/list")
    Single<DeviceConfigResponse> allDeviceConfig(@Header("Authorization") String token, @Query("pagination") String pagination);

//    @GET("device_config/list")
//    Single<DeviceConfigResponse> deviceByPosition(@Header("Authorization") String token, @Query("pagination") String pagination,
//                                            @Query("position_id") int positionId);
}
