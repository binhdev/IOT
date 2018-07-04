package com.smarthome.iot.ui.main.fragment.alldevice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.source.local.DeviceLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceRemoteDataSource;
import com.smarthome.iot.ui.device.adapter.DeviceAdapter;
import com.smarthome.iot.ui.main.fragment.BaseFragment;
import com.smarthome.iot.ui.widget.dialog.DeviceDialog;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class AllDeviceFragment extends BaseFragment implements AllDeviceContract.View {

    private AllDeviceContract.Presenter mPresenter;
    private List<Device> deviceList = new ArrayList<>();
    private RecyclerView rcDeviceResponseListView;
    private DeviceAdapter mAdapter;

    public static AllDeviceFragment newInstance(){
        AllDeviceFragment fragment = new AllDeviceFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alldevice, container, false);
        initGUI(view);

        return view;
    }

    private void initData(){
        DeviceRepository deviceRepository = DeviceRepository.getInstance(DeviceLocalDataSource.getInstance(),
                DeviceRemoteDataSource.getInstance(getContext()));
        mPresenter = new AllDevicePresenter(getContext(), deviceRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.deviceList("false");
    }

    private void initGUI(View view){
        rcDeviceResponseListView = view.findViewById(R.id.rc_device_list);
        mAdapter = new DeviceAdapter(deviceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcDeviceResponseListView.setLayoutManager(mLayoutManager);
        rcDeviceResponseListView.setItemAnimator(new DefaultItemAnimator());
        rcDeviceResponseListView.setAdapter(mAdapter);
        rcDeviceResponseListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        dialogProgress = msgUtil.initCustomDialogProgress(getContext(), null);

        view.findViewById(R.id.fab).setOnClickListener(v -> {
            DeviceDialog addGroupDialog = new DeviceDialog(getContext(), null);
            addGroupDialog.setListener(new DeviceDialog.DeviceDialogListener() {
                @Override
                public void onOkay(Device device) {
                    mPresenter.addDevice(device);
                }

                @Override
                public void onCancel() {

                }
            });

            addGroupDialog.show();
        });

        initData();
    }

    @Override
    public void setDeviceResponseList(List<Device> list) {
        mAdapter.add(list);
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
}
