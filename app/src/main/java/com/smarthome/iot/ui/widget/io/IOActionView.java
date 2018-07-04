package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.model.io.IO;
import com.smarthome.iot.data.model.io.IOAction;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.ui.widget.dialog.IOSelectDialog;
import com.smarthome.iot.ui.widget.io.types.IOType;
import com.smarthome.iot.utils.Flash;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOActionView extends LinearLayout {

    @BindView(R.id.tv_io)
    public TextView tvIO;

    @BindView(R.id.ivv_value)
    public IOValueView ioValueView;

    @BindView(R.id.cb_delete)
    public CheckBox cbDelete;

    private Position mPosition;
    private Device mDevice;
    private IO mIOAction;

    private boolean isDelete = false;

    public IOActionView(Context context) {
        super(context);
        init();
    }

    public IOActionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IOActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.io_action, this);
        ButterKnife.bind(this);

        tvIO.setOnClickListener(view -> {
            IOSelectDialog ioSelectDialog = new IOSelectDialog(getContext(), IOType.HAS_OUTPUT, IOType.IO_NOT_FILTER_BY_DATA_TYPE);
            ioSelectDialog.setIOListener((position, device, io) -> {
                mPosition = position;
                mDevice = device;
                mIOAction = io;
                updateGUI();
            });
            ioSelectDialog.show();
        });

        cbDelete.setOnCheckedChangeListener(((compoundButton, isChecked) -> {
            isDelete = isChecked ? true : false;
        }));
    }

    public boolean getIsSelecteToDelete(){
        return isDelete;
    }

    private void updateGUI(){
        if(mPosition != null && mIOAction != null)
            tvIO.setText(String.format("%s/%s/%s", mPosition.getName(), mIOAction.deviceName,  mIOAction.name));
        ioValueView.setIOSelected(mIOAction);
        ioValueView.setVisibility(View.VISIBLE);
    }



    public IOAction getIoAction(){
        IOAction ioAction = null;
        if(validate())
            ioAction = new IOAction(mIOAction.id, ioValueView.getValue());
        return ioAction;
    }

    public boolean validate(){
        if(mIOAction == null || ioValueView.getValue() == null){
            Flash.pushError("io, value can not empty");
            return false;
        }
        return true;
    }

    /**
     * Edit for action
     * @param action
     */

    public void setActionToEdit(DetailAlarmResponse.Action action){
        initEditInfoIOGUI(action.infoIO);
        initEditValueGUI(action);
    }

    private void initEditInfoIOGUI(DetailAlarmResponse.InfoIO infoIO){
        /**
         * First of all, mFirstIO then updateGUI
         */
        mIOAction = infoIO.io;
        tvIO.setText(String.format("%s / %s / %s", infoIO.position.getName(),
                infoIO.device.getName(), infoIO.io.name));
    }

    private void initEditValueGUI(DetailAlarmResponse.Action action){
        mIOAction = action.infoIO.io;
        ioValueView.setActionToEdit(action);
        updateGUI();
    }
}