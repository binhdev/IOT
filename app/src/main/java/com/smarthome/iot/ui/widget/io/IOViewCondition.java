package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.ui.widget.dialog.IOSelectDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOViewCondition extends LinearLayout {

    @BindView(R.id.io_condition_compare)
    public Spinner spIOConditionCompare;

    @BindView(R.id.io_id)
    public TextView tvIOId;

    @BindView(R.id.io_value_compare)
    public Spinner spIOValueCompare;

    @BindView(R.id.io_value_type)
    public Spinner spIOValueType;

    @BindView(R.id.io_value)
    public EditText mValue;

    @BindView(R.id.cb_delete)
    public CheckBox cbDelete;

    private boolean isDelete = false;

    public IOViewCondition(Context context) {
        super(context);
        init();
    }

    public IOViewCondition(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IOViewCondition(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.io_condition, this);
        ButterKnife.bind(this);

        CustomSpinnerAdapter mConditionCompareAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                getResources().getStringArray(R.array.condition_compare));
        spIOConditionCompare.setAdapter(mConditionCompareAdapter);
        spIOConditionCompare.setSelection(0);

        CustomSpinnerAdapter mValueCompareAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                getResources().getStringArray(R.array.value_compare));
        spIOValueCompare.setAdapter(mValueCompareAdapter);
        spIOValueCompare.setSelection(0);

        CustomSpinnerAdapter mValueTypeAdapter = new CustomSpinnerAdapter(getContext(), R.layout.item_spinner,
                getResources().getStringArray(R.array.value_type));
        spIOValueType.setAdapter(mValueTypeAdapter);
        spIOValueType.setSelection(0);

        /**
         * Set event click
         */

        tvIOId.setOnClickListener(view -> {
            IOSelectDialog ioSelectDialog = new IOSelectDialog(getContext(), IOSelectDialog.TYPE.HAS_INPUT_OUTPUT);
            ioSelectDialog.show();
        });

        cbDelete.setOnCheckedChangeListener(((compoundButton, isChecked) -> {
            isDelete = isChecked ? true : false;
        }));
    }

    public boolean getIsSelecteToDelete(){
        return isDelete;
    }
}
