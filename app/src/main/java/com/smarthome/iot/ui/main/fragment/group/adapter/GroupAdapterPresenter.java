package com.smarthome.iot.ui.main.fragment.group.adapter;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.repository.GroupRepository;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class GroupAdapterPresenter implements GroupAdapterContract.Presenter {

    private Context context;
    private GroupAdapterContract.View mView;
    private GroupRepository mGroupRepository;
    private BaseSchedulerProvider mSchedulerProvider;

    public GroupAdapterPresenter(Context context, GroupRepository groupRepository, BaseSchedulerProvider schedulerProvider,
                                 GroupAdapterContract.View view){
        this.context = Preconditions.checkNotNull(context);
        mGroupRepository = Preconditions.checkNotNull(groupRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
        this.mView = view;
    }

    @Override
    public void deleteGroup(int id) {
        mGroupRepository.deleteGroup(id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleDeletePositionSuccess(),
                        error -> handleDeletePositionFailed());
    }

    @Override
    public void editGroup(Group group) {
        mGroupRepository.editGroup(group)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleEditPositionSuccess(),
                        error -> handleEditPositionFailed());
    }

    private void handleEditPositionSuccess(){
        mView.editGroupSuccess();
    }

    private void handleEditPositionFailed(){
        mView.editGroupFailed();
    }

    private void handleDeletePositionSuccess(){
        mView.deleteGroupSuccess();
    }

    private void handleDeletePositionFailed(){
        mView.deleteGroupFailed();
    }
}
