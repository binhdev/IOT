package com.smarthome.iot.ui.main.fragment.group;

import android.content.Context;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.repository.GroupRepository;
import com.smarthome.iot.data.source.remote.response.GroupListResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

import java.util.List;

public class GroupPresenter implements IGroupContract.IPresenter {
    private IGroupContract.IView mView;
    private GroupRepository mGroupRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public GroupPresenter(Context context, GroupRepository groupRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mGroupRepository = Preconditions.checkNotNull(groupRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }
    @Override
    public void setView(IGroupContract.IView view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void groupList() {
        mGroupRepository.groupList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(groupListResponse -> handleGroupListSuccess(groupListResponse),
                    error -> handleGroupListFailed(error));
    }

    private void handleGroupListSuccess(GroupListResponse groupListResponse){
        mView.setGroupListView(groupListResponse.getData().getGroups());
    }

    private void handleGroupListFailed(Throwable err){

    }
}
