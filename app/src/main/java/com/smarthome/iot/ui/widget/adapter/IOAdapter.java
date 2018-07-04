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
import com.smarthome.iot.data.model.io.IO;

import java.util.List;

public class IOAdapter extends ArrayAdapter<IO> {
    private List<IO> ioList;

    public IOAdapter(@NonNull Context context, int resource, @NonNull List<IO> ioList) {
        super(context, resource, ioList);
        this.ioList = ioList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(ioList.get(position).name);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner_dropdow_general, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(ioList.get(position).name);
        return view;
    }

    @Nullable
    @Override
    public IO getItem(int position) {
        return super.getItem(position);
    }
}
