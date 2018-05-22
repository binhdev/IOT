package com.smarthome.iot.data.source;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.source.remote.response.PositionListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface PositionDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{

        Observable createPosition(@NonNull Position position);

        Observable deletePosition(@NonNull Position position);

        Single<PositionListResponse> positionList();
    }
}