package com.smarthome.iot.ui.device.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.smarthome.iot.R;
import com.smarthome.iot.data.source.remote.response.device.DeviceResponse;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private List<DeviceResponse.Data> dataList;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public SwipeLayout mSwipeLayout;
        public TextView mName;
        public TextView mDescription;

        public ViewHolder(View view){
            super(view);
            mSwipeLayout = view.findViewById(R.id.swipe);
            mName = view.findViewById(R.id.tv_name);
            mDescription = view.findViewById(R.id.tv_description);
        }
    }

    public DeviceAdapter(List<DeviceResponse.Data> list){
        dataList = list;
    }

    @NonNull
    @Override
    public DeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device, parent, false);
        return new DeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeviceResponse.Data deviceResponse = dataList.get(position);
        holder.mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.mSwipeLayout.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onOpen(SwipeLayout layout) {
                super.onOpen(layout);
            }
        });
        holder.mName.setText(deviceResponse.getName());
        holder.mDescription.setText(deviceResponse.getDeviceTypeName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void add(List<DeviceResponse.Data> list){
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(List<DeviceResponse.Data> deviceResponses){
        int position = dataList.indexOf(deviceResponses);
        dataList.remove(position);
        notifyDataSetChanged();
    }
}