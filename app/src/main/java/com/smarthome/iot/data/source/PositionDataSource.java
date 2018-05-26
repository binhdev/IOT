package com.smarthome.iot.data.source;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.position.ListPositionResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface PositionDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{

        Single<BaseResponse> createPosition(@NonNull Position position);

        Single<BaseResponse> deletePosition(@NonNull Position position);

        Single<ListPositionResponse> positionList();
    }
}