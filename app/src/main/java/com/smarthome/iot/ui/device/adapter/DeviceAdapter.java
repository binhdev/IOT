package com.smarthome.iot.ui.device.adapter;

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
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.repository.DeviceRepository;
import com.smarthome.iot.data.source.local.DeviceLocalDataSource;
import com.smarthome.iot.data.source.remote.DeviceRemoteDataSource;
import com.smarthome.iot.ui.widget.dialog.DeviceDialog;
import com.smarthome.iot.utils.rx.SchedulerProvider;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> implements DeviceAdapterContract.View {

    private Context mContext;
    private List<Device> deviceList;
    private DeviceAdapterContract.Presenter mPresenter;

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

    public DeviceAdapter(List<Device> list){
        deviceList = list;
    }

    @NonNull
    @Override
    public DeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_device, parent, false);

        DeviceRepository deviceRepository = DeviceRepository.getInstance(DeviceLocalDataSource.getInstance(),
                DeviceRemoteDataSource.getInstance(parent.getContext()));
        mPresenter = new DeviceAdapterPresenter(mContext, deviceRepository, SchedulerProvider.getInstance(), this);
        return new DeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Device device = deviceList.get(position);
        holder.mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.mSwipeLayout.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onOpen(SwipeLayout layout) {
                super.onOpen(layout);
            }
        });
        holder.mName.setText(device.getName());
        holder.mDescription.setText(device.getDeviceTypeName());

        holder.mButtonEdit.setOnClickListener(v -> {
            DeviceDialog editGroupDialog = new DeviceDialog(mContext, device);
            editGroupDialog.setListener(new DeviceDialog.DeviceDialogListener() {
                @Override
                public void onOkay(Device dev) {
                    mPresenter.editDevice(dev);
                }

                @Override
                public void onCancel() {

                }
            });

            editGroupDialog.show();
        });

        holder.mButtonDelete.setOnClickListener(v -> {
            int [] arrayId = new int[1];
            arrayId[0] = device.getId();
            mPresenter.deleteDevice(arrayId);
        });

    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public void add(List<Device> list){
        deviceList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(List<Device> list){
        int position = deviceList.indexOf(list);
        deviceList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void addDeviceSuccess() {

    }

    @Override
    public void addDeviceFailed() {

    }

    @Override
    public void editDeviceSuccess() {

    }

    @Override
    public void editDeviceFailed() {

    }

    @Override
    public void deleteDeviceSuccess() {

    }

    @Override
    public void deleteDeviceFailed() {

    }
}