package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.IGroupDataSource;
import com.smarthome.iot.data.source.remote.api.IApiGroup;
import com.smarthome.iot.data.source.remote.response.GroupListResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppPrefs;

import io.reactivex.Single;

public class GroupRemoteDataSource implements IGroupDataSource.RemoteDataSource {

    private static GroupRemoteDataSource instance;

    private IApiGroup mApiGroup;

    private Context context;

    public GroupRemoteDataSource(IApiGroup mApiGroup){
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
    public Single<GroupListResponse> groupList() {
        return mApiGroup.groupList(AppPrefs.getInstance(context).getApiToken());
    }
}
