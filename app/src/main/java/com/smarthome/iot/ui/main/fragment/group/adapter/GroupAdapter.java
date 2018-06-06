package com.smarthome.iot.ui.main.fragment.group.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.repository.GroupRepository;
import com.smarthome.iot.data.source.local.GroupLocalDataSource;
import com.smarthome.iot.data.source.remote.GroupRemoteDataSource;
import com.smarthome.iot.ui.widget.dialog.GroupDialog;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> implements GroupAdapterContract.View{

    private Context context;
    private List<Group> groupList;
    private GroupAdapterContract.Presenter mPresenter;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public SwipeLayout mSwipeLayout;
        public TextView mName;
        public TextView mDescription;
        public TextView mButtonEdit;
        public TextView mButtonDelete;

        public ViewHolder(View view){
            super(view);
            mSwipeLayout = view.findViewById(R.id.swipe);
            mName = view.findViewById(R.id.tv_name);
            mDescription = view.findViewById(R.id.tv_description);
            mButtonEdit = view.findViewById(R.id.btn_edit);
            mButtonDelete = view.findViewById(R.id.btn_delete);
        }
    }

    public GroupAdapter(List<Group> list){
        groupList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_group, parent, false);
        GroupRepository groupRepository = GroupRepository.getInstance(GroupLocalDataSource.getInstance(),
                GroupRemoteDataSource.getInstance(parent.getContext()));
        mPresenter = new GroupAdapterPresenter(context, groupRepository, SchedulerProvider.getInstance(), this);

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

        holder.mButtonEdit.setOnClickListener(v -> {
            GroupDialog editGroupDialog = new GroupDialog(context, group);
            editGroupDialog.setListener(new GroupDialog.GroupDialogListener() {
                @Override
                public void onOkay(Group group) {
                    mPresenter.editGroup(group);
                }

                @Override
                public void onCancel() {

                }
            });

            editGroupDialog.show();
        });

        holder.mButtonDelete.setOnClickListener(v -> {
            mPresenter.deleteGroup(group.getId());
        });
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


    @Override
    public void deleteGroupSuccess() {

    }

    @Override
    public void deleteGroupFailed() {

    }

    @Override
    public void editGroupSuccess() {

    }

    @Override
    public void editGroupFailed() {

    }
}
