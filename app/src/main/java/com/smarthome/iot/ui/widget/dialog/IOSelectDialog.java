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
import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.model.io.IO;
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
import com.smarthome.iot.ui.widget.io.types.IOType;
import com.smarthome.iot.utils.collections.Filter;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOSelectDialog extends Dialog{

    private Context mContext;

    @BindView(R.id.sp_position)
    public Spinner spPosition;

    @BindView(R.id.sp_device)
    public Spinner spDevice;

    @BindView(R.id.sp_io)
    public Spinner spIO;

    @BindView(R.id.btn_apply)
    public Button btnApply;

    private int mIOType;

    private boolean isLoading = false;
    private Position mPositionSelected;
    private Device mDeviceSelected;
    private IO mIOSelected;
    private int mDataTypeIO;

    /**
     *
     * @param context
     * @param ioType have three options: HAS_INPUT, HAS_OUTPUT, HAS_INPUT_OUTPUT
     * @param dataTypeIO query all io have the same dataType is dataTypeIO
     */
    public IOSelectDialog(@NonNull Context context, int ioType, int dataTypeIO) {
        super(context);
        this.mContext = context;
        this.mIOType = ioType;
        this.mDataTypeIO = dataTypeIO;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_io);

        initGUI();
    }

    private void initGUI(){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.CENTER);
        ButterKnife.bind(this);

        btnApply.setOnClickListener(view -> {
            if(spPosition.getAdapter().getCount() > 0 && spDevice.getAdapter().getCount() > 0
                    && spIO.getAdapter().getCount() > 0){
                mPositionSelected = (Position) spPosition.getSelectedItem();
                mDeviceSelected = (Device) spDevice.getSelectedItem();
                mIOSelected = (IO) spIO.getSelectedItem();

                if(mDataTypeIO == IOType.IO_NOT_FILTER_BY_DATA_TYPE)
                    ioListener.callBack(mPositionSelected, mDeviceSelected,  mIOSelected);
                else
                    ioFilterByDataTypeListener.callBack(mPositionSelected, mDeviceSelected,  mIOSelected);
            }else{
                Toast.makeText(getContext(), "All field can not empty", Toast.LENGTH_LONG).show();
            }


            dismiss();
        });

        loadPosition();
    }

    private void loadPosition(){
        isLoading = true;
        PositionRepository positionRepository = PositionRepository.getInstance(PositionLocalDataSource.getInstance(),
                PositionRemoteDataSource.getInstance(mContext));
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();

        if(mDataTypeIO == IOType.IO_NOT_FILTER_BY_DATA_TYPE){
            positionRepository.allPosition()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(positionListResponse -> handlePositionResponseSuccess(positionListResponse),
                            error -> handlePositionResponeFailed(error));
        }else{
            positionRepository.allPositionWithDataTypeIO(mDataTypeIO)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(positionListResponse -> handlePositionResponseSuccess(positionListResponse),
                            error -> handlePositionResponeFailed(error));
        }
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
        switch (mIOType){
            case IOType.HAS_INPUT:
                positionListHasIO = Filter.filter(positionList, position -> position.getHasIoInput());
                break;
            case IOType.HAS_OUTPUT:
                positionListHasIO = Filter.filter(positionList, position -> position.getHasIoOutput());
                break;
            case IOType.HAS_INPUT_OUTPUT:
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

        //Query not filter io
        if(mDataTypeIO == IOType.IO_NOT_FILTER_BY_DATA_TYPE){
            deviceRepository.deviceByPosition(positionId)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(deviceResponse -> handleDeviceResponseSuccess(deviceResponse),
                            error -> handleDeviceResponseFailed(error));
        }else{
            deviceRepository.deviceByPositionWithDataTypeIO(positionId, mDataTypeIO)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(deviceResponse -> handleDeviceResponseSuccess(deviceResponse),
                            error -> handleDeviceResponseFailed(error));
        }
    }

    private void fillDeviceToSpinner(List<Device> deviceList){
        List<Device> deviceListHasIO = null;
        switch (mIOType){
            case IOType.HAS_INPUT:
                deviceListHasIO = Filter.filter(deviceList, device -> device.getHasIoInput());
                break;
            case IOType.HAS_OUTPUT:

                deviceListHasIO = Filter.filter(deviceList, device -> device.getHasIoOutput());
                break;
            case IOType.HAS_INPUT_OUTPUT:
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

        if(mDataTypeIO == IOType.IO_NOT_FILTER_BY_DATA_TYPE){
            ioRepository.ioByDevice(deviceId, mIOType)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(ioResponse -> handleIOResponseSuccess(ioResponse),
                            error -> handleIOResponseFailed(error));
        }else{
            ioRepository.ioByDeviceWithDataTypeIO(deviceId, mIOType, mDataTypeIO)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(ioResponse -> handleIOResponseSuccess(ioResponse),
                            error -> handleIOResponseFailed(error));
        }
    }

    private void fillIOToSpinner(List<IO> ioList){
        IOAdapter mAdapter = new IOAdapter(getContext(), R.layout.item_spinner_dropdow_general, ioList);
        spIO.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        spIO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                if(!isLoading){
                    mIOSelected = mAdapter.getItem(pos);
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

    public interface IOListener{
        void callBack(Position position, Device device, IO io);
    }

    public interface IOFilterByDataTypeListener{
        void callBack(Position position, Device device, IO io);
    }

    public void setIOListener(IOListener listener){
        this.ioListener = listener;
    }

    public void setIoFilterByDataTypeListener(IOFilterByDataTypeListener ioFilterByDataTypeListener){
        this.ioFilterByDataTypeListener = ioFilterByDataTypeListener;
    }

    IOListener ioListener;
    IOFilterByDataTypeListener ioFilterByDataTypeListener;
}
