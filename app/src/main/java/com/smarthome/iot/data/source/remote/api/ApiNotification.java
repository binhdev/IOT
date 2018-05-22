package com.smarthome.iot.data.source.remote.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiNotification {
    @GET("notification/list")
    Observable notificationList();
}
