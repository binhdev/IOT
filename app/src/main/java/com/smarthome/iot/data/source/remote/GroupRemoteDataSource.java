package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.source.GroupDataSource;
import com.smarthome.iot.data.source.remote.api.ApiGroup;
import com.smarthome.iot.data.source.remote.response.ListGroupResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

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
    public Single<ListGroupResponse> allGroup() {
        return mApiGroup.allGroup(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()));
    }

    @Override
    public Single addGroup(Group group) {
        return mApiGroup.addGroup(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                group.getName(), group.getDescription());
    }

    @Override
    public Single editGroup(Group group) {
        return mApiGroup.editGroup(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                group.getId(), group.getName());
    }

    @Override
    public Single deleteGroup(int id) {
        return mApiGroup.deleteGroup(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                id);
    }
}
