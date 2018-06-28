package com.smarthome.iot.ui.widget.io;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smarthome.iot.R;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private String[] arrValues;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull String[] arrValues) {
        super(context, resource, arrValues);
        this.arrValues = arrValues;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(arrValues[position]);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner_dropdow_general, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(arrValues[position]);
        return view;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
}
