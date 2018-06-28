package com.smarthome.iot.data.source.remote.api;

import com.smarthome.iot.data.source.remote.response.IOResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiIO {

    @GET("io/list")
    Single<IOResponse> ioByDevice(@Header("Authorization") String token, @Query("pagination") String pagination,
                                      @Query("device_id") int deviceId, @Query("type") int type);

}
