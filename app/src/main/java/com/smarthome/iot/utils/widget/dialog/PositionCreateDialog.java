package com.smarthome.iot.utils.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.smarthome.iot.R;

public class PositionCreateDialog extends Dialog implements View.OnClickListener {

    private EditText editPositionName;
    private EditText editPositionDescription;

    public PositionCreateDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.form_position);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.CENTER);


        Button btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        editPositionName = findViewById(R.id.ed_postion_name);
        editPositionDescription = findViewById(R.id.ed_position_description);
    }

    public void setListener(PositionCreateDialogListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_close:
                listener.onCacel();
                break;
            case R.id.btn_save:
                actionOk();
                break;
        }
        dismiss();
    }

    private void actionOk(){
        String name = editPositionName.getText().toString();
        String description = editPositionDescription.getText().toString();
        listener.onOkay(name, description);
    }
    public interface PositionCreateDialogListener{
        void onOkay(String name, String description);
        void onCacel();
    }

    PositionCreateDialogListener listener;
}
