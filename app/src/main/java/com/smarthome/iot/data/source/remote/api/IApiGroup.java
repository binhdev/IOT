package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.GroupListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiGroup {

    @PUT("group/edit/{id}")
    Observable editGroup(@Path("id") String id);

    @PUT("group/remove_device")
    Observable removeDeviceInGroup(@Query("devices") String devices);

    @Headers({"Content-Type:application/json"})
    @GET("group/list")
    Single<GroupListResponse> groupList(@Header("Token") String token);

    @DELETE("group/delete/{id}")
    Observable deleteGroup(@Path("id") String id);

    @POST("group/add")
    Observable addGroup(@Query("name") String name, @Query("description") String description);

    @PUT("group/add_device")
    Observable addDeviceToGroup(@Query("id") String id, @Query("devices") String devices);

}
