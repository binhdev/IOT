package com.smarthome.iot.ui.main.fragment.alarm;

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
import com.smarthome.iot.data.model.alarm.Alarm;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.local.AlarmLocalDataSource;
import com.smarthome.iot.data.source.remote.AlarmRemoteDataSource;
import com.smarthome.iot.ui.alarm.AddAlarmActivity;
import com.smarthome.iot.ui.main.fragment.alarm.adapter.AlarmAdapter;
import com.smarthome.iot.utils.navigator.Navigator;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment implements AlarmContract.View {

    private AlarmContract.Presenter mPresenter;
    private List<Alarm> mAlarmList = new ArrayList<>();
    private RecyclerView rcAlarmListView;
    private AlarmAdapter mAdapter;

    public static AlarmFragment newInstance(){
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        initGUI(view);

        return view;
    }

    private void initGUI(View view){
        rcAlarmListView = view.findViewById(R.id.rc_alarm_list);
        mAdapter = new AlarmAdapter(mAlarmList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcAlarmListView.setLayoutManager(mLayoutManager);
        rcAlarmListView.setItemAnimator(new DefaultItemAnimator());
        rcAlarmListView.setAdapter(mAdapter);
        rcAlarmListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        view.findViewById(R.id.fab).setOnClickListener(v -> {
            Navigator navigator = new Navigator(getActivity());
            navigator.startActivity(AddAlarmActivity.class);
        });

        initData();
    }

    private void initData(){
        AlarmRepository alarmRepository = AlarmRepository.getInstance(AlarmLocalDataSource.getInstance(),
                AlarmRemoteDataSource.getInstance(getContext()));
        mPresenter = new AlarmPresenter(getContext(), alarmRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.allAlarm();
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showLoginError(Throwable throwable) {

    }

    @Override
    public void startDashboardActivity() {

    }

    @Override
    public void setAlarmToView(List<Alarm> alarmList) {
        mAdapter.add(alarmList);
    }
}
