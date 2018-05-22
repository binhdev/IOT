package com.smarthome.iot.data.source.remote.api;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiAlarm {

    @GET("alarm/list")
    Observable alarmList();

    @DELETE("alarm/delete")
    Observable deleteAlarm(@Query("id") String id);

    @GET("alarm/detail/{id}")
    Observable alarmDetail(@Path("id") String id);

    @PUT("alarm/edit/{id}")
    Observable editAlarm(@Path("name") String name, @Query("priority") String priority);

    @POST("alarm/add")
    Observable addAlarm(@Query("name") String name, @Query("code") String code, @Query("position_id") String position_id);

}
