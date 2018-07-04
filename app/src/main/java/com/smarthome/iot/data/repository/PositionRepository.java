package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.source.PositionDataSource;
import com.smarthome.iot.data.source.local.PositionLocalDataSource;
import com.smarthome.iot.data.source.remote.PositionRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.PositionResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class PositionRepository implements PositionDataSource.LocalDataSource, PositionDataSource.RemoteDataSource  {

    private static PositionRepository instance;

    @NonNull
    private PositionLocalDataSource mPositionLocalDataSource;

    @NonNull
    private PositionRemoteDataSource mPositionRemoteDataSource;

    private PositionRepository(@NonNull PositionLocalDataSource positionLocalDataSource, @NonNull PositionRemoteDataSource positionRemoteDataSource){
        mPositionLocalDataSource = positionLocalDataSource;
        mPositionRemoteDataSource = positionRemoteDataSource;
    }

    public static synchronized PositionRepository getInstance(@NonNull PositionLocalDataSource positionLocalDataSource, @NonNull PositionRemoteDataSource positionRemoteDataSource){
        if(instance == null){
            instance = new PositionRepository(positionLocalDataSource, positionRemoteDataSource);
        }
        return instance;
    }

    @Override
    public Single<BaseResponse> createPosition(Position position) {
        return mPositionRemoteDataSource.createPosition(position);
    }

    @Override
    public Single<BaseResponse> editPosition(Position position) {
        return mPositionRemoteDataSource.editPosition(position);
    }

    @Override
    public Single<BaseResponse> deletePosition(int[] ids) {
        return mPositionRemoteDataSource.deletePosition(ids);
    }

    @Override
    public Single<PositionResponse> positionTreeStructure() {
        return mPositionRemoteDataSource.positionTreeStructure();
    }

    @Override
    public Single<PositionResponse> allPosition() {
        return mPositionRemoteDataSource.allPosition();
    }

    @Override
    public Single<PositionResponse> allPositionWithDataTypeIO(int dataTypeIO) {
        return mPositionRemoteDataSource.allPositionWithDataTypeIO(dataTypeIO);
    }
}
