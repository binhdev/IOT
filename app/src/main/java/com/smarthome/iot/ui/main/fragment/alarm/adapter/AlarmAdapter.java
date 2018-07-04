package com.smarthome.iot.ui.main.fragment.alarm.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.smarthome.iot.R;
import com.smarthome.iot.data.model.alarm.Alarm;
import com.smarthome.iot.data.repository.AlarmRepository;
import com.smarthome.iot.data.source.local.AlarmLocalDataSource;
import com.smarthome.iot.data.source.remote.AlarmRemoteDataSource;
import com.smarthome.iot.ui.alarm.EditAlarmActivity;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.helper.StringHelper;
import com.smarthome.iot.utils.navigator.Navigator;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.List;

public class AlarmAdapter  extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> implements AlarmAdapterContract.View{

    private Context mContext;
    private List<Alarm> alarmList;
    private AlarmAdapterContract.Presenter mPresenter;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public SwipeLayout mSwipeLayout;
        public TextView mPriority;
        public TextView mName;
        public TextView mNotification;
        public TextView mButtonEdit;
        public TextView mButtonDelete;

        public ViewHolder(View view){
            super(view);
            mSwipeLayout = view.findViewById(R.id.swipe);
            mPriority = view.findViewById(R.id.tv_priority);
            mName = view.findViewById(R.id.tv_name);
            mNotification = view.findViewById(R.id.tv_notification);
            mButtonEdit = view.findViewById(R.id.btn_edit);
            mButtonDelete = view.findViewById(R.id.btn_delete);
        }
    }

    public AlarmAdapter(List<Alarm> list){
        alarmList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_alarm, parent, false);
        AlarmRepository groupRepository = AlarmRepository.getInstance(AlarmLocalDataSource.getInstance(),
                AlarmRemoteDataSource.getInstance(parent.getContext()));
        mPresenter = new AlarmAdapterPresenter(mContext, groupRepository, SchedulerProvider.getInstance(), this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        holder.mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.mSwipeLayout.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onOpen(SwipeLayout layout) {
                super.onOpen(layout);
            }
        });
        holder.mPriority.setText(StringHelper.ConcatString("Priority: ", alarm.getPriority().toString()));
        holder.mName.setText(alarm.getName());
        holder.mNotification.setText(alarm.getNotification() == 1 ? mContext.getResources().getString(R.string.alarm_on)
                : mContext.getResources().getString(R.string.alarm_of));
        holder.mNotification.setBackgroundResource(alarm.getNotification() == 1 ? R.drawable.alarm_on_background
        : R.drawable.alarm_of_background);

        holder.mButtonDelete.setOnClickListener(v -> {
            int [] arrayId = new int[1];
            arrayId[0] = alarm.getAgentId();
            mPresenter.deleteAlarm(arrayId);
        });

        holder.mButtonEdit.setOnClickListener(v -> {
            Navigator navigator = new Navigator((Activity)mContext);
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.ALARM_OBJECT, alarm);
            navigator.startActivity(EditAlarmActivity.class, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public void add(List<Alarm> list){
        alarmList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(Alarm group){
        int position = alarmList.indexOf(group);
        alarmList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void deleteAlarmSuccess() {

    }

    @Override
    public void deleteAlarmFailed() {

    }
}