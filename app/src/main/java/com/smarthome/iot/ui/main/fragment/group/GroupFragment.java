package com.smarthome.iot.ui.main.fragment.group;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.repository.GroupRepository;
import com.smarthome.iot.data.source.local.GroupLocalDataSource;
import com.smarthome.iot.data.source.remote.GroupRemoteDataSource;
import com.smarthome.iot.ui.main.fragment.BaseFragment;
import com.smarthome.iot.ui.main.fragment.group.adapter.GroupAdapter;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends BaseFragment implements GroupContract.View {

    private GroupContract.Presenter mPresenter;
    private List<Group> groupList = new ArrayList<>();
    private RecyclerView rcGroupListView;
    private GroupAdapter mAdapter;

    public static GroupFragment newInstance(){
        GroupFragment fragment = new GroupFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        initGUI(view);

        return view;
    }

    private void initGUI(View view){
        rcGroupListView = view.findViewById(R.id.rc_group_list);
        mAdapter = new GroupAdapter(groupList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcGroupListView.setLayoutManager(mLayoutManager);
        rcGroupListView.setItemAnimator(new DefaultItemAnimator());
        rcGroupListView.setAdapter(mAdapter);
        rcGroupListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initData();
    }

    private void initData(){
        GroupRepository groupRepository = GroupRepository.getInstance(GroupLocalDataSource.getInstance(),
                GroupRemoteDataSource.getInstance(getContext()));
        mPresenter = new GroupPresenter(getContext(), groupRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.groupList();
    }



    @Override
    public void showLoadingIndicator() {
        dialogProgress.show();
    }

    @Override
    public void hideLoadingIndicator() {
        dialogProgress.dismiss();
    }

    @Override
    public void showLoginError(Throwable throwable) {

    }

    @Override
    public void startDashboardActivity() {

    }

    @Override
    public void setGroupListView(List<Group> groupList) {
        mAdapter.add(groupList);
    }
}
