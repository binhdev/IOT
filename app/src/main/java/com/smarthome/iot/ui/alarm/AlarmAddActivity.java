package com.smarthome.iot.ui.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.smarthome.iot.R;
import com.smarthome.iot.ui.widget.io.IOViewAction;
import com.smarthome.iot.ui.widget.io.IOViewCondition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmAddActivity extends AppCompatActivity {

    @BindView(R.id.alarm_name)
    public EditText mAlarmName;

    @BindView(R.id.alarm_priority)
    public EditText mAlarmPriority;

    @BindView(R.id.alarm_notification)
    public Switch mAlarmNotification;

    @BindView(R.id.list_io_condition)
    public LinearLayout mAlarmListIOCondition;

    @BindView(R.id.btn_add_condition)
    public ImageView mButtonAddCondition;

    @BindView(R.id.btn_delete_condition)
    public ImageView mButtonDeleteCondition;

    @BindView(R.id.btn_add_action)
    public ImageView mButtonAddAction;

    @BindView(R.id.btn_delete_action)
    public ImageView mButtonDeleteAction;

    @BindView(R.id.list_io_action)
    public LinearLayout mAlarmListIOAction;

    private List<IOViewCondition> ioViewConditionList = new ArrayList<>();
    private List<IOViewAction> ioViewActionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

        initGUI();
    }

    private void initGUI(){
        //For condition view control
        mButtonAddCondition.setOnClickListener(view -> {
            IOViewCondition ioView = new IOViewCondition(this);
            mAlarmListIOCondition.addView(ioView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ioViewConditionList.add(ioView);
        });

        mButtonDeleteCondition.setOnClickListener(view -> {
            for (Iterator<IOViewCondition> iterator = ioViewConditionList.iterator(); iterator.hasNext();){
                IOViewCondition ioViewCondition = iterator.next();
                if(ioViewCondition.getIsSelecteToDelete()){
                    iterator.remove();
                    renderIOViewCondition();
                }
            }
        });

        //For action view control
        mButtonAddAction.setOnClickListener(view -> {
            IOViewAction ioView = new IOViewAction(this);
            mAlarmListIOAction.addView(ioView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ioViewActionList.add(ioView);
        });

        mButtonDeleteAction.setOnClickListener(view -> {
            for (Iterator<IOViewAction> iterator = ioViewActionList.iterator(); iterator.hasNext();){
                IOViewAction ioViewAction = iterator.next();
                if(ioViewAction.getIsSelecteToDelete()){
                    iterator.remove();
                    renderIOViewAction();
                }
            }
        });
    }

    private void renderIOViewCondition(){
        mAlarmListIOCondition.removeAllViews();
        for(IOViewCondition ioViewCondition : ioViewConditionList){
            mAlarmListIOCondition.addView(ioViewCondition);
        }
    }

    private void renderIOViewAction(){
        mAlarmListIOAction.removeAllViews();
        for(IOViewAction ioViewAction : ioViewActionList){
            mAlarmListIOAction.addView(ioViewAction);
        }
    }
}
