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
import com.smarthome.iot.data.model.Position;

import java.util.List;

public class PositionAdapter extends ArrayAdapter<Position> {
    private List<Position> positionList;

    public PositionAdapter(@NonNull Context context, int resource, @NonNull List<Position> positionList) {
        super(context, resource, positionList);
        this.positionList = positionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(positionList.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_spinner_dropdow_general, parent, false);
        TextView tvPositionName = view.findViewById(R.id.tv_name);
        tvPositionName.setText(positionList.get(position).getName());
        return view;
    }

    @Nullable
    @Override
    public Position getItem(int position) {
        return super.getItem(position);
    }
}
