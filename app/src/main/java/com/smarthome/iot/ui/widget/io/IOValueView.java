package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.io.IO;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.ui.widget.io.types.DataType;
import com.smarthome.iot.ui.widget.io.types.Option;
import com.smarthome.iot.ui.widget.io.types.ValueType;
import com.smarthome.iot.utils.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOValueView extends LinearLayout {

    @BindView(R.id.sw_value)
    public Switch swValue;

    @BindView(R.id.card_view_value)
    public CardView cvValue;

    @BindView(R.id.sp_value)
    public Spinner spValue;

    @BindView(R.id.ed_value)
    public EditText edValue;

    private Object mValue;

    private int mDataType;

    private IO mIOSelected;

    public IOValueView(Context context) {
        super(context);

        initGUI();
    }

    public IOValueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initGUI();
    }

    public IOValueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initGUI(){
        inflate(getContext(), R.layout.io_value_view, this);
        ButterKnife.bind(this);
    }

    public void setIOSelected(IO io){
        this.mIOSelected = io;
        this.mDataType = io.dataType;
        updateGUI();
    }

    private void updateGUI(){
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

    //For analog
    private void caseAnalog(){
        swValue.setVisibility(View.GONE);
        cvValue.setVisibility(View.GONE);
        edValue.setVisibility(View.VISIBLE);
        renderAnalogValueGUI();
    }

    private void renderAnalogValueGUI(){
        String value = mIOSelected.currentValue.toString();
        edValue.setText(value);
    }

    private void caseDigital(){
        swValue.setVisibility(View.VISIBLE);
        cvValue.setVisibility(View.GONE);
        edValue.setVisibility(View.GONE);
        renderDigitalValueGUI();
    }

    private void renderDigitalValueGUI(){
        int value = Integer.valueOf(mIOSelected.currentValue.toString());
        swValue.setChecked(value == 0 ? false : true);
    }

    //For analog, string
    private void caseString(){
        swValue.setVisibility(View.GONE);
        cvValue.setVisibility(View.GONE);
        edValue.setVisibility(View.VISIBLE);
        renderStringValueGUI();
    }

    private void renderStringValueGUI(){
        String value = mIOSelected.currentValue.toString();
        edValue.setText(value);
    }

    //For multiSelection
    private void caseMultiSelection(){
        swValue.setVisibility(View.GONE);
        cvValue.setVisibility(View.VISIBLE);
        edValue.setVisibility(View.GONE);
        renderMultiSelectionGUI();
    }

    private void renderMultiSelectionGUI(){
        List<IO.DataMultiSelection> dataMultiSelectionList = mIOSelected.dataMultiSelection;
        List<Option> valueSpinner = new ArrayList<>();
        for (IO.DataMultiSelection dataMultiSelection : dataMultiSelectionList) {
            Option option = new Option(dataMultiSelection.name, dataMultiSelection.order);
            valueSpinner.add(option);
        }

        CustomSpinnerAdapter valueSpinnerAdapter = new CustomSpinnerAdapter(getContext(),
                R.layout.item_spinner_dropdow_general,
                (valueSpinner.toArray(new Option[valueSpinner.size()])));
        spValue.setAdapter(valueSpinnerAdapter);
        valueSpinnerAdapter.notifyDataSetChanged();
    }

    public Object getValue(){
        Option option = null;
        switch (mDataType){
            case DataType.TYPE_ANALOG:
                mValue = edValue.getText();
                break;
            case DataType.TYPE_DIGITAL:
                mValue = swValue.isSelected() ? 1 : 0;
                break;
            case DataType.TYPE_STRING:
                option = (Option) spValue.getSelectedItem();
                mValue = option.value;
                break;
            case DataType.TYPE_MULTISELECTION:
                option = (Option) spValue.getSelectedItem();
                mValue = option.value;
                break;
        }

        return mValue;
    }

    /**
     * For edit
     */

    public void setActionToEdit(DetailAlarmResponse.Action action){
        mIOSelected = action.infoIO.io;
        mDataType = mIOSelected.dataType;
        updateGUI();
        switch (mDataType){
            case DataType.TYPE_ANALOG:
                edValue.setText(action.value.toString());
                break;
            case DataType.TYPE_DIGITAL:
                swValue.setChecked(Boolean.valueOf(action.value.toString()));
                break;
            case DataType.TYPE_STRING:
                edValue.setText(action.value.toString());
                break;
            case DataType.TYPE_MULTISELECTION:
                spValue.setSelection(SpinnerHelper.findPositionSpinner(spValue,
                        Double.valueOf(action.value.toString()).intValue()));
                break;
        }
    }
}
