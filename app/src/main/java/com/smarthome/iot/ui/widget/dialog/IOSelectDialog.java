package com.smarthome.iot.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.model.IO;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.repository.IORepository;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.data.source.local.DeviceLocalDataSource;
import com.smarthome.iot.data.source.local.IOLocalDataSource;
import com.smarthome.iot.data.source.local.PositionLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceRemoteDataSource;
import com.smarthome.iot.data.source.remote.IORemoteDataSource;
import com.smarthome.iot.data.source.remote.PositionRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.DeviceResponse;
import com.smarthome.iot.data.source.remote.response.IOResponse;
import com.smarthome.iot.data.source.remote.response.PositionResponse;
import com.smarthome.iot.ui.widget.adapter.DeviceAdapter;
import com.smarthome.iot.ui.widget.adapter.IOAdapter;
import com.smarthome.iot.ui.widget.adapter.PositionAdapter;
import com.smarthome.iot.utils.collections.Filter;
import com.smarthome.iot.utils.collections.Predicate;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOSelectDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    @BindView(R.id.sp_position)
    public Spinner spPosition;

    @BindView(R.id.sp_device)
    public Spinner spDevice;

    @BindView(R.id.sp_io)
    public Spinner spIO;

    @BindView(R.id.btn_apply)
    public Button btnApply;

    public static enum TYPE { HAS_INPUT, HAS_OUTPUT, HAS_INPUT_OUTPUT};
    private TYPE type;

    private boolean isLoading = false;

    public IOSelectDialog(@NonNull Context context, TYPE type) {
        super(context);
        this.mContext = context;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_io);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.CENTER);
        ButterKnife.bind(this);

        loadPosition();
    }

    private void loadPosition(){
        isLoading = true;
        PositionRepository positionRepository = PositionRepository.getInstance(PositionLocalDataSource.getInstance(),
                PositionRemoteDataSource.getInstance(mContext));
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        positionRepository.allPosition()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(positionListResponse -> handlePositionResponseSuccess(positionListResponse),
                        error -> handlePositionResponeFailed(error));
    }

    private void handlePositionResponseSuccess(PositionResponse positionListResponse){
        isLoading = false;
        fillPositionToSpinner(positionListResponse.getPositionList());
    }

    private void handlePositionResponeFailed(Throwable err){
        isLoading = false;
    }

    private void fillPositionToSpinner(List<Position> positionList){
        List<Position> positionListHasIO = new ArrayList<>();
        switch (type){
            case HAS_INPUT:
                positionListHasIO = Filter.filter(positionList, position -> position.getHasIoInput());
                break;
            case HAS_OUTPUT:

                positionListHasIO = Filter.filter(positionList, position -> position.getHasIoOutput());
                break;
            case HAS_INPUT_OUTPUT:
                positionListHasIO = Filter.filter(positionList, position -> position.getHasIoInput() || position.getHasIoOutput());
                break;
        }

        PositionAdapter mAdapter = new PositionAdapter(getContext(), R.layout.item_spinner_dropdow_general, positionListHasIO);
        spPosition.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        spPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if(!isLoading){
                    Position selectedPosition = mAdapter.getItem(pos);
                    loadDevice(selectedPosition.getId());
                }else{
                    Toast.makeText(getContext(),"Please wait", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadDevice(int positionId){
        isLoading = true;
        DeviceRepository deviceRepository = DeviceRepository.getInstance(DeviceLocalDataSource.getInstance(),
                DeviceRemoteDataSource.getInstance(mContext));
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        deviceRepository.deviceByPosition(positionId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(deviceResponse -> handleDeviceResponseSuccess(deviceResponse),
                        error -> handleDeviceResponseFailed(error));
    }

    private void fillDeviceToSpinner(List<Device> deviceList){
        List<Device> deviceListHasIO = null;
        switch (type){
            case HAS_INPUT:
                deviceListHasIO = Filter.filter(deviceList, device -> device.getHasIoInput());
                break;
            case HAS_OUTPUT:

                deviceListHasIO = Filter.filter(deviceList, device -> device.getHasIoOutput());
                break;
            case HAS_INPUT_OUTPUT:
                deviceListHasIO = Filter.filter(deviceList, device -> device.getHasIoInput() || device.getHasIoOutput());
                break;
        }

        DeviceAdapter mAdapter = new DeviceAdapter(getContext(), R.layout.item_spinner_dropdow_general, deviceListHasIO);
        spDevice.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        spDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if(!isLoading){
                    Device selectedDevice = mAdapter.getItem(pos);
                    loadIO(selectedDevice.getId());
                }else{
                    Toast.makeText(getContext(),"Please wait", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void handleDeviceResponseSuccess(DeviceResponse deviceResponse){
        isLoading = false;
        fillDeviceToSpinner(deviceResponse.getDeviceList());
    }


    private void handleDeviceResponseFailed(Throwable err){
        isLoading = false;
    }

    private void loadIO(int deviceId){
        isLoading = true;
        IORepository ioRepository = IORepository.getInstance(IOLocalDataSource.getInstance(),
                IORemoteDataSource.getInstance(mContext));
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        ioRepository.ioByDevice(deviceId, 1)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(ioResponse -> handleIOResponseSuccess(ioResponse),
                        error -> handleIOResponseFailed(error));
    }

    private void fillIOToSpinner(List<IO> ioList){
        IOAdapter mAdapter = new IOAdapter(getContext(), R.layout.item_spinner_dropdow_general, ioList);
        spIO.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        spPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if(!isLoading){
                    IO selectedIO = mAdapter.getItem(pos);
                }else{
                    Toast.makeText(getContext(),"Please wait", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void handleIOResponseSuccess(IOResponse ioResponse){
        isLoading = false;
        fillIOToSpinner(ioResponse.getIOList());
    }


    private void handleIOResponseFailed(Throwable err){
        isLoading = false;
    }

    @Override
    public void onClick(View view) {

    }
}
