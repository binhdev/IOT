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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.data.source.local.PositionLocalDataSource;
import com.smarthome.iot.data.source.remote.PositionRemoteDataSource;
import com.smarthome.iot.data.source.remote.response.position.PositionResponse;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;


public class PositionEditDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private EditText editPositionName;
    private EditText editPositionDescription;
    private Spinner spinnerPositionParent;
    private Position mPosition;
    private List<Position> mPositionList;

    public PositionEditDialog(@NonNull Context context, Position position) {
        super(context);
        this.mContext = context;
        this.mPosition = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_position_edit);
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

        editPositionName = findViewById(R.id.ed_postion_name);
        editPositionDescription = findViewById(R.id.ed_position_description);
        spinnerPositionParent = findViewById(R.id.sp_position_parent);

        editPositionName.setText(mPosition.getName());
        editPositionDescription.setText(mPosition.getDescription());

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
        int parentId = 1;

        /**
         * tree_struct api return position without parentId
         * list api return position with parentId
         */
        for(int i=0; i < mPositionList.size(); i++){
            Position p = mPositionList.get(i);
            positionNames.add(p.getName());
            if(p.getId() == mPosition.getId())
                parentId = p.getParentId();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_item, positionNames);
        spinnerPositionParent.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinnerPositionParent.setSelection(findParentIdIndex(parentId));


    }

    private int findParentIdIndex(int parentId){
        int parentIndexInList = 0;
        for(int i=0; i < mPositionList.size(); i++){
            if(parentId == mPositionList.get(i).getId())
                parentIndexInList = i;
        }
        return parentIndexInList;
    }

    private void handlePositionListFailed(Throwable error){

    }

    public void setListener(PositionEditDialog.PositionEditDialogListener listener){
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
        mPosition.setName(editPositionName.getText().toString());
        mPosition.setDescription(editPositionDescription.getText().toString());
        mPosition.setParentId(mPositionList.get(spinnerPositionParent.getSelectedItemPosition()).getId());
        listener.onOkay(mPosition);
    }
    public interface PositionEditDialogListener{
        void onOkay(Position position);
        void onCancel();
    }

    PositionEditDialog.PositionEditDialogListener listener;
}
