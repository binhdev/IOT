package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.ui.widget.dialog.IOSelectDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOViewAction extends LinearLayout {

    @BindView(R.id.io_id)
    public TextView tvIOId;

    @BindView(R.id.io_value_type)
    public Switch swIOValueType;

    @BindView(R.id.io_value)
    public Spinner spIOValue;

    @BindView(R.id.cb_delete)
    public CheckBox cbDelete;

    private boolean isDelete = false;

    public IOViewAction(Context context) {
        super(context);
        init();
    }

    public IOViewAction(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IOViewAction(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.io_action, this);
        ButterKnife.bind(this);

        tvIOId.setOnClickListener(view -> {
            IOSelectDialog ioSelectDialog = new IOSelectDialog(getContext(), IOSelectDialog.TYPE.HAS_OUTPUT);
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