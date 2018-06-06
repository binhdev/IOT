package com.smarthome.iot.ui.widget.dialog;

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
import com.smarthome.iot.data.model.Group;

public class GroupDialog extends Dialog implements View.OnClickListener {

    private EditText editGroupName;
    private EditText editGroupDescription;
    private Group mGroup;

    public GroupDialog(@NonNull Context context, Group group) {
        super(context);
        if(group != null)
            this.mGroup = group;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_group);
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

        editGroupName = findViewById(R.id.ed_group_name);
        editGroupDescription = findViewById(R.id.ed_group_description);

        /**
         * mGroupp is null if add dialog
         * mGroup is not null if edit dialog
         */
        if(mGroup != null) {
            editGroupName.setText(mGroup.getName());
            editGroupDescription.setText(mGroup.getDescription());
        }
    }

    public void setListener(GroupDialog.GroupDialogListener listener){
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
        if(mGroup != null) {
            mGroup.setName(editGroupName.getText().toString());
            mGroup.setDescription(editGroupDescription.getText().toString());
            listener.onOkay(mGroup);
        }else{
            Group group = new Group();
            group.setName(editGroupName.getText().toString());
            group.setDescription(editGroupDescription.getText().toString());
            listener.onOkay(group);
        }
    }
    public interface GroupDialogListener{
        void onOkay(Group group);
        void onCancel();
    }

    GroupDialog.GroupDialogListener listener;
}
