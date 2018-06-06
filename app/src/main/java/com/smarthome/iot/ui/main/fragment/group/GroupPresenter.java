package com.smarthome.iot.ui.main.fragment.group;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.model.Group;
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
    public void allGroup() {
        mView.showLoadingIndicator();
        mGroupRepository.allGroup()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(groupListResponse -> handleGroupListSuccess(groupListResponse),
                    error -> handleGroupListFailed());
    }

    @Override
    public void deleteGroup(int id) {
        mGroupRepository.deleteGroup(id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(s -> handleDeleteGroupSuccess(),
                        error -> handleDeleteGroupFailed());
    }

    @Override
    public void addGroup(Group group) {
        mGroupRepository.addGroup(group)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(s -> handleAddGroupSuccess(),
                        error -> handleAddGroupFailed());
    }

    @Override
    public void editGroup(Group group) {
        mGroupRepository.editGroup(group)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(s -> handleEditGroupSuccess(),
                        error -> handleEditGroupFailed());
    }

    private void handleGroupListSuccess(ListGroupResponse groupListResponse){
        mView.setGroupListView(groupListResponse.getData().getGroups());
        mView.hideLoadingIndicator();
    }

    private void handleGroupListFailed(){

    }

    private void handleAddGroupSuccess(){

    }

    private void handleAddGroupFailed(){

    }

    private void handleEditGroupSuccess(){

    }

    private void handleEditGroupFailed(){

    }

    private void handleDeleteGroupSuccess(){

    }

    private void handleDeleteGroupFailed(){

    }
}
