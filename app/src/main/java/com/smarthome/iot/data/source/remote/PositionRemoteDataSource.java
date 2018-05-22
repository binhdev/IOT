package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.source.PositionDataSource;
import com.smarthome.iot.data.source.remote.api.ApiPosition;
import com.smarthome.iot.data.source.remote.response.PositionListResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppPrefs;

import io.reactivex.Observable;
import io.reactivex.Single;

public class PositionRemoteDataSource implements PositionDataSource.RemoteDataSource {

    private static PositionRemoteDataSource instance;

    private ApiPosition mApiPosition;

    private Context context;

    public PositionRemoteDataSource(ApiPosition mApiPosition){
        this.mApiPosition = mApiPosition;
    }

    public static synchronized PositionRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new PositionRemoteDataSource(AppServiceClient.getPositionRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Observable createPosition(Position position) {
        return mApiPosition.createPostion(AppPrefs.getInstance(context).getApiToken(),
                position.getName(), position.getDescription(), position.getParentId());
    }

    @Override
    public Observable deletePosition(Position position) {
        return mApiPosition.deletePosition(AppPrefs.getInstance(context).getApiToken(),
                position.getId().toString());
    }

    @Override
    public Single<PositionListResponse> positionList() {
        return mApiPosition.positionList(AppPrefs.getInstance(context).getApiToken());
    }
}