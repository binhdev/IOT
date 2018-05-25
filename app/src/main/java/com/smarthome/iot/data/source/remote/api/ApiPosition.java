package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.position.PositionCreateResponse;
import com.smarthome.iot.data.source.remote.response.position.PositionListResponse;

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
    Single<PositionListResponse> positionList(@Header("Token") String token);

    @PUT("position/delete")
    Observable deletePosition(@Header("Token") String token, @Path("id") String id);

    @POST("position/add")
    Single<PositionCreateResponse> createPostion(@Header("Token") String token, @Query("name") String name,
                                                 @Query("description") String description, @Query("parent_id") int parent_id);

}
