package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.PositionResponse;

import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPosition {

    @GET("position/tree_structure")
    Single<PositionResponse> positionTreeStructure(@Header("Authorization") String token, @Query("pagination") String pagination);

    @GET("position/list")
    Single<PositionResponse> allPosition(@Header("Authorization") String token, @Query("pagination") String pagination);

    @DELETE("position/delete")
    Single<BaseResponse> deletePosition(@Header("Authorization") String token, @Query("id[]") int[] id);

    @POST("position/add")
    Single<BaseResponse> createPostion(@Header("Authorization") String token, @Query("name") String name,
                                                 @Query("description") String description, @Query("parent_id") int parent_id);

    @PUT("position/edit/{id}")
    Single<BaseResponse> editPosition(@Header("Authorization") String token, @Path("id") int positionId,
                                      @Query("name") String name, @Query("description") String description,
                                      @Query("parent_id") int parent_id);

}
