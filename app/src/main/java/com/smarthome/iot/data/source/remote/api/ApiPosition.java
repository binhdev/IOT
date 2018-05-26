package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.position.ListPositionResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPosition {

    @PUT("position/edit/{id}")
    Observable editPostion(@Path("id") String id);

    @GET("position/tree_structure")
    Single<ListPositionResponse> positionList(@Header("Token") String token);

    @PUT("position/delete")
    Single<BaseResponse> deletePosition(@Header("Token") String token, @Path("id") String id);

    @POST("position/add")
    Single<BaseResponse> createPostion(@Header("Token") String token, @Query("name") String name,
                                                 @Query("description") String description, @Query("parent_id") int parent_id);

}
