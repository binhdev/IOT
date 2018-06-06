package com.smarthome.iot.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.model.DeviceConfig;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.repository.DeviceConfigRepository;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.data.source.local.DeviceConfigLocalDataSource;
import com.smarthome.iot.data.source.local.PositionLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceConfigRemoteDataSource;
import com.smarthome.iot.data.source.remote.PositionRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.deviceconfig.DeviceConfigResponse;
import com.smarthome.iot.data.source.remote.response.position.PositionResponse;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;


public class DeviceDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private EditText editDeviceName;
    private AutoCompleteTextView autoCompleteDeviceCode;
    private Spinner spinnerDevicePosition;
    private Device mDevice;
    private List<Position> mPositionList;

    public DeviceDialog(@NonNull Context context, Device device) {
        super(context);
        this.mContext = context;
        if(device != null)
            this.mDevice = device;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_device);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.CENTER);

        initGUI();
    }

    private void initGUI(){
        Button btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        editDeviceName = findViewById(R.id.ed_device_name);
        autoCompleteDeviceCode = findViewById(R.id.ed_device_code);
        spinnerDevicePosition = findViewById(R.id.sp_device_position);
        /**
         * mPosition is not null if dialog edit
         */
        if(mDevice != null) {
            editDeviceName.setText(mDevice.getName());
            autoCompleteDeviceCode.setText(mDevice.getCode());
        }

       initData();
    }

    private void initData(){
        initPositionData();
        initAutoCompleteData();
    }

    public void setListener(DeviceDialog.DeviceDialogListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_close:
                listener.onCancel();
                break;
            case R.id.btn_save:
                actionOk();
                break;
        }
        dismiss();
    }

    private void actionOk(){
        if(mDevice != null) {
            mDevice.setName(editDeviceName.getText().toString());
            mDevice.setCode(autoCompleteDeviceCode.getText().toString());
            mDevice.setGroupId(mPositionList.get(spinnerDevicePosition.getSelectedItemPosition()).getId());
            listener.onOkay(mDevice);
        }else{
            Device device = new Device();
            device.setName(editDeviceName.getText().toString());
            device.setCode(autoCompleteDeviceCode.getText().toString());
            listener.onOkay(device);
        }
    }
    public interface DeviceDialogListener{
        void onOkay(Device device);
        void onCancel();
    }

    DeviceDialog.DeviceDialogListener listener;

    /**
     * Request all position from server
     */

    private void initPositionData(){
        PositionRepository positionRepository = PositionRepository.getInstance(PositionLocalDataSource.getInstance(),
                PositionRemoteDataSource.getInstance(mContext));
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        positionRepository.allPosition()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(positionListResponse -> handlePositionListSuccess(positionListResponse),
                        error -> handlePositionListFailed(error));
    }



    private void handlePositionListSuccess(PositionResponse listPositionResponse){
        mPositionList = listPositionResponse.getPositionList();
        List<String> positionNames = new ArrayList<>();
        int positionId = 1;

        /**
         * tree_struct api return position without parentId
         * list api return position with parentId
         */
        for(int i=0; i < mPositionList.size(); i++){
            Position p = mPositionList.get(i);
            positionNames.add(p.getName());
            //Find positionId when edit device
            if(mDevice != null) {
                if (p.getId() == mDevice.getPositionId())
                    positionId = p.getId();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_item, positionNames);
        spinnerDevicePosition.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //Set selected postion on spinner
        if(mDevice != null)
            spinnerDevicePosition.setSelection(findPositionIdIndex(positionId));


    }

    private int findPositionIdIndex(int parentId){
        int parentIndexInList = 0;
        for(int i=0; i < mPositionList.size(); i++){
            if(parentId == mPositionList.get(i).getId())
                parentIndexInList = i;
        }
        return parentIndexInList;
    }

    private void handlePositionListFailed(Throwable error){

    }

    /**
     * Request all device config from server
     */

    private void initAutoCompleteData(){
        DeviceConfigRepository deviceConfigRepository = DeviceConfigRepository.getInstance(DeviceConfigLocalDataSource.getInstance(),
                DeviceConfigRemoteDataSource.getInstance(mContext));
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        deviceConfigRepository.allDeviceConfig()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(deviceConfigResponse -> handleAllDeviceConfigSuccess(deviceConfigResponse),
                        error -> handlePositionListFailed(error));
    }

    private void handleAllDeviceConfigSuccess(DeviceConfigResponse deviceConfigResponse){
        List<DeviceConfig> deviceConfigList = deviceConfigResponse.getDeviceConfigList();
        String arrCode[] = new String[deviceConfigList.size()];
        for(int i=0; i < deviceConfigList.size(); i++){
            arrCode[i] = deviceConfigList.get(i).getCode();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (mContext,android.R.layout.select_dialog_item, arrCode);

        autoCompleteDeviceCode.setThreshold(2);
        autoCompleteDeviceCode.setAdapter(adapter);
    }
}
