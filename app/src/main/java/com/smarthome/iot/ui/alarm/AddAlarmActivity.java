package com.smarthome.iot.ui.alarm;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.io.IOAction;
import com.smarthome.iot.data.model.io.IOCondition;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.local.AlarmLocalDataSource;
import com.smarthome.iot.data.source.remote.AlarmRemoteDataSource;
import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.ui.base.BaseActivity;
import com.smarthome.iot.ui.widget.io.IOActionView;
import com.smarthome.iot.ui.widget.io.IOConditionView;
import com.smarthome.iot.utils.Flash;
import com.smarthome.iot.utils.NotificationStatus;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAlarmActivity extends BaseActivity implements AddAlarmContract.View {

    @BindView(R.id.ed_alarm_name)
    public EditText edAlarmName;

    @BindView(R.id.ed_alarm_priority)
    public EditText edAlarmPriority;

    @BindView(R.id.sp_alarm_notification)
    public Switch spAlarmNotification;

    @BindView(R.id.btn_save)
    public TextView mButtonSaveAlarm;

    @BindView(R.id.tv_error)
    public TextView tvError;

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

    private List<IOConditionView> ioViewConditionList = new ArrayList<>();
    private List<IOActionView> ioViewActionList = new ArrayList<>();

    private AddAlarmContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        AlarmRepository alarmRepository = AlarmRepository.getInstance(AlarmLocalDataSource.getInstance(),
                AlarmRemoteDataSource.getInstance(this));
        mPresenter = new AddAlarmPresenter(this, alarmRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        initGUI();
    }

    private void initGUI(){
        dialogProgress = msgUtil.initCustomDialogProgress(this, null);
        mButtonSaveAlarm.setOnClickListener(view -> {
            createAlarmRequest();
        });

        //For condition view control
        mButtonAddCondition.setOnClickListener(view -> {
            IOConditionView ioView = new IOConditionView(this);
            mAlarmListIOCondition.addView(ioView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ioViewConditionList.add(ioView);
            renderIOViewCondition();
        });

        mButtonDeleteCondition.setOnClickListener(view -> {
            for (Iterator<IOConditionView> iterator = ioViewConditionList.iterator(); iterator.hasNext();){
                IOConditionView ioViewCondition = iterator.next();
                if(ioViewCondition.getIsSelecteToDelete()){
                    iterator.remove();
                    renderIOViewCondition();
                }
            }
        });

        //For action view control
        mButtonAddAction.setOnClickListener(view -> {
            IOActionView ioView = new IOActionView(this);
            mAlarmListIOAction.addView(ioView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ioViewActionList.add(ioView);
        });

        mButtonDeleteAction.setOnClickListener(view -> {
            for (Iterator<IOActionView> iterator = ioViewActionList.iterator(); iterator.hasNext();){
                IOActionView ioViewAction = iterator.next();

                if(ioViewAction.getIsSelecteToDelete()){
                    iterator.remove();
                    renderIOViewAction();
                }
            }
        });
    }

    private void renderIOViewCondition(){
        mAlarmListIOCondition.removeAllViews();
        int index = 0;
        for(IOConditionView ioViewCondition : ioViewConditionList){
            if(index == 0)
                ioViewCondition.spConditionCompare.setVisibility(View.GONE);
            mAlarmListIOCondition.addView(ioViewCondition);
            index++;
        }
    }

    private void renderIOViewAction(){
        mAlarmListIOAction.removeAllViews();
        for(IOActionView ioViewAction : ioViewActionList){
            mAlarmListIOAction.addView(ioViewAction);
        }
    }

    private void createAlarmRequest(){
        List<IOCondition> ioConditionList = new ArrayList<>();
        List<IOAction> ioActionList = new ArrayList<>();
        for(int i = 0; i < ioViewConditionList.size(); i++){
            IOConditionView ioViewCondition = ioViewConditionList.get(i);
            if(ioViewCondition.validate()){
                IOCondition ioCondition = ioViewCondition.getIoCondition();
                if(ioCondition != null){
                    ioCondition.order = (i+1);
                    ioConditionList.add(ioCondition);
                }
            }
        }

        for(int i = 0; i < ioViewActionList.size(); i++){
            IOActionView ioActionView = ioViewActionList.get(i);
            if(ioActionView.validate()){
                IOAction ioAction = ioActionView.getIoAction();
                ioActionList.add(ioAction);
            }
        }

        if(validate()){
            String name = edAlarmName.getText().toString();
            int priority = Integer.valueOf(edAlarmPriority.getText().toString());
            int notification = spAlarmNotification.isChecked() ? NotificationStatus.CHECKED : NotificationStatus.UNCHECKED;
            AddAlarmRequest addAlarmRequest = new AddAlarmRequest(name, priority, notification,
                    ioConditionList, ioActionList);
            mPresenter.addAlarm(addAlarmRequest);
        }else{
            tvError.setText(Flash.getErrors());
            Flash.clearErrors();
            tvError.setVisibility(View.VISIBLE);
            tvError.postDelayed(()-> tvError.setVisibility(View.GONE), 3000);
        }

    }

    private boolean validate(){
        if(edAlarmName.getText().toString().trim().equals("")){
            Flash.pushError("Can not empty alarm name");
            return false;

        }
        if(ioViewConditionList.size() == 0 || ioViewActionList.size() == 0){
            Flash.pushError("Can not empty condition or action");
            return false;
        }
        return true;
    }

    @Override
    public void addAlarmSuccess() {
        onBackPressed();
    }

    @Override
    public void addAlarmFailed() {
        tvError.setText(Flash.getErrors());
        Flash.clearErrors();
        tvError.postDelayed(()-> tvError.setVisibility(View.GONE), 3000);
    }

    @Override
    public void showLoadingIndicator() {
        dialogProgress.show();
    }

    @Override
    public void hideLoadingIndicator() {
        dialogProgress.dismiss();
    }

    @Override
    public void showLoginError(Throwable throwable) {

    }

    @Override
    public void startDashboardActivity() {

    }
}
