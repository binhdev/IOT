package com.smarthome.iot.data.repository;

import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.source.GroupDataSource;
import com.smarthome.iot.data.source.local.GroupLocalDataSource;
import com.smarthome.iot.data.source.remote.GroupRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.ListGroupResponse;

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
    public Single<ListGroupResponse> allGroup() {
        return mGroupRemoteDataSource.allGroup();
    }

    @Override
    public Single addGroup(Group group) {
        return mGroupRemoteDataSource.addGroup(group);
    }

    @Override
    public Single editGroup(Group group) {
        return mGroupRemoteDataSource.editGroup(group);
    }

    @Override
    public Single deleteGroup(int id) {
        return mGroupRemoteDataSource.deleteGroup(id);
    }
}
