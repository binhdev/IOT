package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.GroupDataSource;
import com.smarthome.iot.data.source.remote.api.ApiGroup;
import com.smarthome.iot.data.source.remote.response.group.ListGroupResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppPrefs;

import io.reactivex.Single;

public class GroupRemoteDataSource implements GroupDataSource.RemoteDataSource {

    private static GroupRemoteDataSource instance;

    private ApiGroup mApiGroup;

    private Context context;

    public GroupRemoteDataSource(ApiGroup mApiGroup){
        this.mApiGroup = mApiGroup;
    }

    public static synchronized GroupRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new GroupRemoteDataSource(AppServiceClient.getGroupRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<ListGroupResponse> groupList() {
        return mApiGroup.groupList(AppPrefs.getInstance(context).getApiToken());
    }
}
