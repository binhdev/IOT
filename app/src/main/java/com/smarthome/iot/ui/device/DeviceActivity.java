package com.smarthome.iot.ui.device;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.smarthome.iot.R;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.source.local.DeviceLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.device.ListDeviceResponse;
import com.smarthome.iot.ui.base.BaseActivity;
import com.smarthome.iot.ui.device.adapter.DeviceAdapter;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class DeviceActivity extends BaseActivity implements DeviceContract.View {

    private DeviceContract.Presenter mPresenter;
    private List<ListDeviceResponse.Data> dataList = new ArrayList<>();
    private RecyclerView rcDeviceResponseListView;
    private DeviceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_device);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initGUI();
    }

    private void initData(){
        DeviceRepository deviceRepository = DeviceRepository.getInstance(DeviceLocalDataSource.getInstance(),
                DeviceRemoteDataSource.getInstance(this));
        mPresenter = new DevicePresenter(this, deviceRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.deviceList("false");

        dialogProgress = msgUtil.initCustomDialogProgress(this, null);
    }

    private void initGUI(){
        rcDeviceResponseListView = findViewById(R.id.rc_device_list);
        mAdapter = new DeviceAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcDeviceResponseListView.setLayoutManager(mLayoutManager);
        rcDeviceResponseListView.setItemAnimator(new DefaultItemAnimator());
        rcDeviceResponseListView.setAdapter(mAdapter);
        rcDeviceResponseListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        initData();
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
    public void setDeviceResponseList(List<ListDeviceResponse.Data> dataList) {
        Gson gson = new Gson();
        Log.i("list device", gson.toJson(dataList));
        mAdapter.add(dataList);
    }
}
