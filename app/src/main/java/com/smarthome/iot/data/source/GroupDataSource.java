package com.smarthome.iot.data.source;

import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.ListGroupResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface GroupDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{
        Single<ListGroupResponse> allGroup();

        Single<BaseResponse> addGroup(@NonNull Group group);

        Single<BaseResponse> editGroup(@NonNull Group group);

        Single<BaseResponse> deleteGroup(@NonNull int id);
    }
}
