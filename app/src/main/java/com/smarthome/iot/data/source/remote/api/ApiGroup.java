package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.group.ListGroupResponse;

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

public interface ApiGroup {

    @POST("group/add")
    Single<BaseResponse> addGroup(@Header("Authorization") String token, @Query("name") String name,
                                  @Query("description") String description);

    @PUT("group/edit/{id}")
    Single<BaseResponse> editGroup(@Header("Authorization") String token, @Path("id") int id, @Query("name") String name);

    @DELETE("group/delete/{id}")
    Single<BaseResponse> deleteGroup(@Header("Authorization") String token, @Path("id") int id);

    @DELETE("group/remove_device")
    Single<BaseResponse> removeDeviceInGroup(@Header("Authorization") String token, @Query("devices") String devices);

    @Headers({"Content-Type:application/json"})
    @GET("group/list")
    Single<ListGroupResponse> allGroup(@Header("Authorization") String token);

    @PUT("group/add_device")
    Single<BaseResponse> addDeviceToGroup(@Header("Authorization") String token, @Query("id") String id,
                                @Query("devices") String devices);

}
