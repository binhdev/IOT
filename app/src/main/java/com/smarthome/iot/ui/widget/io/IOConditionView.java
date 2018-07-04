package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.model.io.IO;
import com.smarthome.iot.data.model.io.IOCondition;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.ui.widget.dialog.IOSelectDialog;
import com.smarthome.iot.ui.widget.io.types.CompareType;
import com.smarthome.iot.ui.widget.io.types.ConditionType;
import com.smarthome.iot.ui.widget.io.types.DataType;
import com.smarthome.iot.ui.widget.io.types.IOType;
import com.smarthome.iot.ui.widget.io.types.Option;
import com.smarthome.iot.ui.widget.io.types.ValueType;
import com.smarthome.iot.utils.Flash;
import com.smarthome.iot.utils.helper.SpinnerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOConditionView extends LinearLayout {

    @BindView(R.id.sp_condition_compare)
    public Spinner spConditionCompare;

    @BindView(R.id.tv_first_io)
    public TextView tvFirstIO;

    @BindView(R.id.sp_value_compare)
    public Spinner spValueCompare;

    @BindView(R.id.sp_value_type)
    public Spinner spValueType;

    @BindView(R.id.cb_delete)
    public CheckBox cbDelete;

    @BindView(R.id.second_io_view)
    public SecondIOCompareView secondIOCompareView;

    private boolean isDelete = false;

    private Position mFirstPostion;
    private Device mFirstDevice;
    private IO mFirstIO;

    Option[] conditionTypeList = { new Option("and", ConditionType.AND), new Option("or", ConditionType.OR) };

    Option [] valueCompareAnalogList = { new Option(">", CompareType.GREATER_THAN)
            ,new Option(">=", CompareType.GREATER_THAN_OR_EQUAL)
            ,new Option("<", CompareType.LESS_THAN)
            ,new Option("<=", CompareType.LESS_THAN_OR_EQUAL)
            ,new Option("=", CompareType.EQUAL),
    };

    Option []  valueCompareNoAnalogList = { new Option("=", CompareType.EQUAL) };

    Option [] valueTypeList = { new Option(getResources().getString(R.string.fix_value), ValueType.FIX_VALUE)
            ,new Option(getResources().getString(R.string.select_io_type), ValueType.SELECT_IO)};

    public IOConditionView(Context context) {
        super(context);
        init();
    }

    public IOConditionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IOConditionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.io_condition, this);
        ButterKnife.bind(this);

        /**
         * Set event click
         */

        tvFirstIO.setOnClickListener(view -> {
            IOSelectDialog ioSelectDialog = new IOSelectDialog(getContext(), IOType.HAS_INPUT, IOType.IO_NOT_FILTER_BY_DATA_TYPE);
            ioSelectDialog.setIOListener((position, device, io) -> {
                mFirstPostion = position;
                mFirstDevice = device;
                mFirstIO = io;
                updateGUI();
            });
            ioSelectDialog.show();
        });

        cbDelete.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            isDelete = isChecked ? true : false;
        });
    }

    public boolean getIsSelecteToDelete(){
        return isDelete;
    }

    private void updateGUI(){
        if(mFirstIO != null && mFirstPostion != null)
            tvFirstIO.setText(String.format("%s/%s/%s", mFirstPostion.getName(), mFirstIO.deviceName, mFirstIO.name));
        initConditionCompareGUI();
        initValueCompareGUI();
        initValueTypeGUI();
    }


    private void initConditionCompareGUI(){
        CustomSpinnerAdapter mConditionCompareAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                conditionTypeList);
        spConditionCompare.setAdapter(mConditionCompareAdapter);
        mConditionCompareAdapter.notifyDataSetChanged();
    }

    private void initValueCompareGUI(){
        CustomSpinnerAdapter valueCompareAdapter = null;
        if(mFirstIO.dataType == DataType.TYPE_ANALOG){
            valueCompareAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                    valueCompareAnalogList);
        }else{
            valueCompareAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                    valueCompareNoAnalogList);
        }
        spValueCompare.setAdapter(valueCompareAdapter);
        valueCompareAdapter.notifyDataSetChanged();
    }

    private void initValueTypeGUI(){
        CustomSpinnerAdapter mValueTypeAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                valueTypeList);
        spValueType.setAdapter(mValueTypeAdapter);
        mValueTypeAdapter.notifyDataSetChanged();


        spValueType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Option option = mValueTypeAdapter.getItem(pos);
                secondIOCompareView.changeCondition(mFirstIO.dataType, option.value);
                secondIOCompareView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public IOCondition getIoCondition(){
        IOCondition ioCondition = null;
        if(validate()){
            int conditionCompare = ((Option)spConditionCompare.getSelectedItem()).value;
            int valueCompare = ((Option)spValueCompare.getSelectedItem()).value;
            int valueType  = ((Option)spValueType.getSelectedItem()).value;
            ioCondition = new IOCondition(0, conditionCompare,
                    mFirstIO.id, valueCompare,
                    valueType, secondIOCompareView.getValue().toString());
        }

        return ioCondition;
    }

    public boolean validate(){
        if(mFirstIO == null || secondIOCompareView.getValue() == null) {
            Flash.pushError("IO, value can not empty");
            return false;
        }
        return true;
    }


    public void setConditionToEdit(DetailAlarmResponse.Condition condition){
        initEditConditionCompareGUI(condition.conditionCompare);
        initEditInfoIOGUI(condition.infoIO);
        initEditValueCompareGUI(condition.valueCompare);
        initEditValueTypeGUI(condition.valueType);
        initEditValueGUI(condition);
    }

    private void initEditConditionCompareGUI(int value){
        spConditionCompare.setSelection(SpinnerHelper.findPositionSpinner(spConditionCompare, value));
    }

    private void initEditInfoIOGUI(DetailAlarmResponse.InfoIO infoIO){
        /**
         * First of all, mFirstIO then updateGUI
         */
        mFirstIO = infoIO.io;
        updateGUI();
        tvFirstIO.setText(String.format("%s / %s / %s", infoIO.position.getName(), infoIO.device.getName(), infoIO.io.name));
    }

    private void initEditValueCompareGUI(int value){
        spValueCompare.setSelection(SpinnerHelper.findPositionSpinner(spValueCompare, value));
    }

    private void initEditValueTypeGUI(int value){
        spValueType.setSelection(SpinnerHelper.findPositionSpinner(spValueType, value));
    }

    private void initEditValueGUI(DetailAlarmResponse.Condition condition){
        secondIOCompareView.setConditionToEdit(condition);
    }
}
