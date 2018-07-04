package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.io.IO;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.ui.widget.dialog.IOSelectDialog;
import com.smarthome.iot.ui.widget.io.types.DataType;
import com.smarthome.iot.ui.widget.io.types.IOType;
import com.smarthome.iot.ui.widget.io.types.ValueType;
import com.smarthome.iot.utils.helper.SpinnerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondIOCompareView extends LinearLayout {

    @BindView(R.id.tv_second_io)
    public TextView tvSecondIO;

    @BindView(R.id.ll_fix_value)
    public LinearLayout llFixValue;

    @BindView(R.id.sw_second_io)
    public Switch swSecondIO;

    @BindView(R.id.ed_second_io)
    public EditText edSecondIO;

    @BindView(R.id.sp_second_io)
    public Spinner spSecondIO;

    @BindView(R.id.card_second_io)
    public CardView cvSecondIO;

    private Object mValue;

    private int mDataType;

    private int mValueType;

    private IO mSecondIO;

    public SecondIOCompareView(Context context) {
        super(context);
    }

    public SecondIOCompareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SecondIOCompareView, 0, 0);
        try {
            mDataType = arr.getInteger(R.styleable.SecondIOCompareView_dataType, DataType.TYPE_ANALOG);
            mValueType = arr.getInteger(R.styleable.SecondIOCompareView_valueType, ValueType.FIX_VALUE);
        }finally {
            arr.recycle();
        }

        initGUI();
    }

    public SecondIOCompareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initGUI(){
        inflate(getContext(), R.layout.second_io_compare_layout, this);
        ButterKnife.bind(this);
        updateGUI();
    }

    public void changeCondition(int dataType, int valueType){
        this.mDataType = dataType;
        this.mValueType = valueType;
        updateGUI();
    }

    private void updateGUI(){
        if(mValueType == ValueType.FIX_VALUE){
            caseFixValue();
        }else{
            caseSelectIO();
        }
    }

    private void caseFixValue(){
        tvSecondIO.setVisibility(View.GONE);
        llFixValue.setVisibility(View.VISIBLE);
        switch (mDataType){
            case DataType.TYPE_ANALOG:
                caseAnalog();
                break;
            case DataType.TYPE_DIGITAL:
                caseDigital();
                break;
            case DataType.TYPE_STRING:
                caseString();
                break;
            case DataType.TYPE_MULTISELECTION:
                caseMultiSelection();
                break;
        }
    }

    private void caseSelectIO(){
        tvSecondIO.setVisibility(View.VISIBLE);
        llFixValue.setVisibility(View.GONE);

        tvSecondIO.setOnClickListener(view -> {
            IOSelectDialog ioSelectDialog = new IOSelectDialog(getContext(), IOType.HAS_INPUT, mDataType);
            ioSelectDialog.setIoFilterByDataTypeListener((position, device, io) -> {
                mValue = io.id;
                mSecondIO = io;
                tvSecondIO.setText(String.format("%s/%s/%s", position.getName(), io.deviceName, io.name));
            });
            ioSelectDialog.show();
        });
    }

    private void caseDigital(){
        swSecondIO.setVisibility(View.VISIBLE);
        edSecondIO.setVisibility(View.GONE);
        cvSecondIO.setVisibility(View.GONE);
    }
    //For analog
    private void caseAnalog(){
        swSecondIO.setVisibility(View.GONE);
        edSecondIO.setVisibility(View.VISIBLE);
        cvSecondIO.setVisibility(View.GONE);
        edSecondIO.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    //For analog, string
    private void caseString(){
        caseAnalog();
        edSecondIO.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    //For multiSelection
    private void caseMultiSelection(){
        swSecondIO.setVisibility(View.GONE);
        edSecondIO.setVisibility(View.GONE);
        cvSecondIO.setVisibility(View.VISIBLE);
    }

    public Object getValue(){
        if(mValueType == ValueType.SELECT_IO){
            mValue = mSecondIO.id;
        }else{
            //Fix value
            switch (mDataType){
                case DataType.TYPE_ANALOG:
                    mValue = edSecondIO.getText().toString().trim().equals("") ? null : edSecondIO.getText().toString();
                    break;
                case DataType.TYPE_DIGITAL:
                    mValue = swSecondIO.isSelected() ? true : false;
                    break;
                case DataType.TYPE_STRING:
                    mValue = edSecondIO.getText().toString().trim().equals("") ? null : edSecondIO.getText().toString();
                    break;
                case DataType.TYPE_MULTISELECTION:
                    mValue = spSecondIO.getSelectedItem();
                    break;
            }
        }

        return mValue;
    }

    /**
     * For edit action
     */

    public void setConditionToEdit(DetailAlarmResponse.Condition condition){
        mDataType = condition.infoIO.io.dataType;
        changeCondition(mDataType, condition.valueType);
        switch (mDataType){
            case DataType.TYPE_ANALOG:
                edSecondIO.setText(condition.value.toString());
                break;
            case DataType.TYPE_DIGITAL:
                swSecondIO.setChecked(Boolean.valueOf(condition.value.toString()));
                break;
            case DataType.TYPE_STRING:
                edSecondIO.setText(condition.value.toString());
                break;
            case DataType.TYPE_MULTISELECTION:
                spSecondIO.setSelection(SpinnerHelper.findPositionSpinner(spSecondIO,
                        Double.valueOf(condition.value.toString()).intValue()));
                break;
        }

        //infoValue = null if valueType is not SELECT_IO
        if(condition.valueType == ValueType.SELECT_IO){
            mSecondIO = condition.infoValue.io;
            tvSecondIO.setText(String.format("%s/%s/%s", condition.infoValue.position.getName(),
                    condition.infoValue.device.getName(), condition.infoValue.io.name));
        }

    }

}
