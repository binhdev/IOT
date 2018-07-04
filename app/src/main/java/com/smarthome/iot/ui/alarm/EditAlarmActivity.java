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
import com.smarthome.iot.data.model.alarm.Alarm;
import com.smarthome.iot.data.model.io.IOAction;
import com.smarthome.iot.data.model.io.IOCondition;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.local.AlarmLocalDataSource;
import com.smarthome.iot.data.source.remote.AlarmRemoteDataSource;
import com.smarthome.iot.data.source.remote.request.AddAlarmRequest;
import com.smarthome.iot.data.source.remote.response.DetailAlarmResponse;
import com.smarthome.iot.ui.base.BaseActivity;
import com.smarthome.iot.ui.widget.io.IOActionView;
import com.smarthome.iot.ui.widget.io.IOConditionView;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.Flash;
import com.smarthome.iot.utils.NotificationStatus;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditAlarmActivity extends BaseActivity implements EditAlarmContract.View {

    @BindView(R.id.ed_alarm_name)
    public EditText edAlarmName;

    @BindView(R.id.ed_alarm_priority)
    public EditText edAlarmPriority;

    @BindView(R.id.sw_alarm_notification)
    public Switch swAlarmNotification;

    @BindView(R.id.btn_update)
    public TextView mButtonUpdateAlarm;

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

    private Alarm mAlarm;

    private EditAlarmContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);
        ButterKnife.bind(this);

        mAlarm = (Alarm)getIntent().getSerializableExtra(AppConstants.ALARM_OBJECT);

        AlarmRepository alarmRepository = AlarmRepository.getInstance(AlarmLocalDataSource.getInstance(),
                AlarmRemoteDataSource.getInstance(this));
        mPresenter = new EditAlarmPresenter(this, alarmRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        initGUI();
        detailAlarm();
    }

    private void initGUI(){
        dialogProgress = msgUtil.initCustomDialogProgress(this, null);
        mButtonUpdateAlarm.setOnClickListener(view -> {
            updateAlarmRequest();
        });

        //For condition view control
        mButtonAddCondition.setOnClickListener(view -> {
            IOConditionView ioView = new IOConditionView(this);
            mAlarmListIOCondition.addView(ioView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ioViewConditionList.add(ioView);
            renderIOViewConditionGUI();
        });

        mButtonDeleteCondition.setOnClickListener(view -> {
            for (Iterator<IOConditionView> iterator = ioViewConditionList.iterator(); iterator.hasNext();){
                IOConditionView ioViewCondition = iterator.next();
                if(ioViewCondition.getIsSelecteToDelete()){
                    iterator.remove();
                    renderIOViewConditionGUI();
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
                    renderIOViewActionGUI();
                }
            }
        });
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

    @Override
    public void editAlarmSuccess() {
        onBackPressed();
    }

    @Override
    public void editAlarmFailed() {
        tvError.setText(Flash.getErrors());
        Flash.clearErrors();
        tvError.postDelayed(()-> tvError.setVisibility(View.GONE), 3000);
    }

    private void renderIOViewConditionGUI(){
        mAlarmListIOCondition.removeAllViews();
        int index = 0;
        for(IOConditionView ioViewCondition : ioViewConditionList){
            if(index == 0)
                ioViewCondition.spConditionCompare.setVisibility(View.GONE);
            mAlarmListIOCondition.addView(ioViewCondition);
            index++;
        }
    }

    private void renderIOViewActionGUI(){
        mAlarmListIOAction.removeAllViews();
        for(IOActionView ioViewAction : ioViewActionList){
            mAlarmListIOAction.addView(ioViewAction);
        }
    }

    private void updateAlarmRequest(){
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
            int notification = swAlarmNotification.isChecked() ? NotificationStatus.CHECKED : NotificationStatus.UNCHECKED;
            AddAlarmRequest addAlarmRequest = new AddAlarmRequest(name, priority, notification,
                    ioConditionList, ioActionList);
            mPresenter.editAlarm(mAlarm.getId(), addAlarmRequest);
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

    /**
     * For edit
     */


    private void detailAlarm(){
        mPresenter.detailAlarm(mAlarm.getId());
        edAlarmName.setText(mAlarm.getName());
        edAlarmPriority.setText(mAlarm.getPriority().toString());
        swAlarmNotification.setChecked(mAlarm.getNotification() == NotificationStatus.CHECKED ? true : false);
    }

    @Override
    public void loadDetailAlarmSuccess(DetailAlarmResponse detailAlarmResponse) {
        initEditIOViewCondition(detailAlarmResponse.getData().conditionList);
        initEditIOViewAction(detailAlarmResponse.getData().actionList);
    }

    @Override
    public void loadDetailAlarmFailed() {

    }

    private void initEditIOViewCondition(List<DetailAlarmResponse.Condition> conditionList){
        for (DetailAlarmResponse.Condition condition : conditionList) {
            IOConditionView ioConditionView = new IOConditionView(this);
            ioConditionView.setConditionToEdit(condition);
            ioViewConditionList.add(ioConditionView);
        }
        renderIOViewConditionGUI();
    }

    private void initEditIOViewAction(List<DetailAlarmResponse.Action> actionList){
        for (DetailAlarmResponse.Action action : actionList) {
            IOActionView ioActionView = new IOActionView(this);
            ioActionView.setActionToEdit(action);
            ioViewActionList.add(ioActionView);
        }
        renderIOViewActionGUI();
    }
}