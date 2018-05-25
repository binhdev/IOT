package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.GroupDataSource;
import com.smarthome.iot.data.source.local.GroupLocalDataSource;
import com.smarthome.iot.data.source.remote.GroupRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.group.GroupListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class GroupRepository implements GroupDataSource.LocalDataSource, GroupDataSource.RemoteDataSource  {

    private static GroupRepository instance;

    @NonNull
    private GroupLocalDataSource mGroupLocalDataSource;

    @NonNull
    private GroupRemoteDataSource mGroupRemoteDataSource;

    private GroupRepository(@NonNull GroupLocalDataSource groupLocalDataSource, @NonNull GroupRemoteDataSource groupRemoteDataSource){
        mGroupLocalDataSource = groupLocalDataSource;
        mGroupRemoteDataSource = groupRemoteDataSource;
    }

    public static synchronized GroupRepository getInstance(@NonNull GroupLocalDataSource groupLocalDataSource, @NonNull GroupRemoteDataSource groupRemoteDataSource){
        if(instance == null){
            instance = new GroupRepository(groupLocalDataSource, groupRemoteDataSource);
        }
        return instance;
    }

    @Override
    public Observable insertGroup(User user) {
        return null;
    }

    @Override
    public Observable deleteGroup(User user) {
        return null;
    }

    @Override
    public Single<GroupListResponse> groupList() {
        return mGroupRemoteDataSource.groupList();
    }
}
