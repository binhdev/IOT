package com.smarthome.iot.ui.main.fragment.group.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<Group> groupList;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public SwipeLayout mSwipeLayout;
        public TextView mName;
        public TextView mDescription;
        Button mButtonDelete;

        public ViewHolder(View view){
            super(view);
            mSwipeLayout = view.findViewById(R.id.swipe);
            mName = view.findViewById(R.id.tv_name);
            mDescription = view.findViewById(R.id.tv_description);
//            mButtonDelete = view.findViewById(R.id.delete);
        }
    }

    public GroupAdapter(List<Group> list){
        groupList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = groupList.get(position);
        holder.mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.mSwipeLayout.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onOpen(SwipeLayout layout) {
                super.onOpen(layout);
            }
        });
        holder.mName.setText(group.getName());
        holder.mDescription.setText(group.getDescription());
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void add(List<Group> list){
        groupList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(Group group){
        int position = groupList.indexOf(group);
        groupList.remove(position);
        notifyDataSetChanged();
    }
}
