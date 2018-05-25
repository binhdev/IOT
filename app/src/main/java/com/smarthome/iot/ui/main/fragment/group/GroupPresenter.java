package com.smarthome.iot.ui.main.fragment.group;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.GroupRepository;
import com.smarthome.iot.data.source.remote.response.group.ListGroupResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class GroupPresenter implements GroupContract.Presenter {
    private GroupContract.View mView;
    private GroupRepository mGroupRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public GroupPresenter(Context context, GroupRepository groupRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mGroupRepository = Preconditions.checkNotNull(groupRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }
    @Override
    public void setView(GroupContract.View view) {
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
        mView.showLoadingIndicator();
        mGroupRepository.groupList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(groupListResponse -> handleGroupListSuccess(groupListResponse),
                    error -> handleGroupListFailed(error));
    }

    private void handleGroupListSuccess(ListGroupResponse groupListResponse){
        mView.setGroupListView(groupListResponse.getData().getGroups());
        mView.hideLoadingIndicator();
    }

    private void handleGroupListFailed(Throwable err){

    }
}
