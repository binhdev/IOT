package com.smarthome.iot.data.source;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.remote.response.GroupListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface IGroupDataSource {

    interface LocalDataSource{

        Observable insertGroup(@NonNull User user);

        Observable deleteGroup(@NonNull User user);
    }

    interface RemoteDataSource{
        Single<GroupListResponse> groupList();
    }
}
