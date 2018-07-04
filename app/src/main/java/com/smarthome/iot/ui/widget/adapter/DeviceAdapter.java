package com.smarthome.iot.ui.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.device.Device;

import java.util.List;

public class DeviceAdapter extends ArrayAdapter<Device> {
    private List<Device> deviceList;

    public DeviceAdapter(@NonNull Context context, int resource, @NonNull List<Device> deviceList) {
        super(context, resource, deviceList);
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(deviceList.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner_dropdow_general, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(deviceList.get(position).getName());
        return view;
    }

    @Nullable
    @Override
    public Device getItem(int position) {
        return super.getItem(position);
    }
}
