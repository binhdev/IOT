package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.AlarmResponse;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiAlarm {

    @GET("alarm/list")
    Single<AlarmResponse> allAlarm(@Header("Authorization") String token, @Query("pagination") String pagination);

    @DELETE("alarm/delete")
    Single<BaseResponse> deleteAlarm(@Header("Authorization") String token, @Query("id[]") int[] id);

    @GET("alarm/detail/{id}")
    Single<DetailAlarmResponse> detailAlarm(@Header("Authorization") String token, @Path("id") int id);

    @PUT("alarm/edit/{id}")
    Single<BaseResponse> editAlarm(@Header("Authorization") String token,@Path("id") int alarmId, @Body AddAlarmRequest body);

    @POST("alarm/add")
    Single<BaseResponse> addAlarm(@Header("Authorization") String token, @Body AddAlarmRequest body);

}
