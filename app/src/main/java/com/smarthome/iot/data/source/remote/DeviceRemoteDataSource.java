package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.source.DeviceDataSource;
import com.smarthome.iot.data.source.remote.api.ApiDevice;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.device.DeviceResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class DeviceRemoteDataSource implements DeviceDataSource.RemoteDataSource {

    private static DeviceRemoteDataSource instance;

    private ApiDevice mApiDevice;

    private Context context;

    public DeviceRemoteDataSource(ApiDevice apiDevice){
        this.mApiDevice = apiDevice;
    }

    public static synchronized DeviceRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new DeviceRemoteDataSource(AppServiceClient.getDeviceRemoteInstance(context));
        }
        return instance;
    }
    @Override
    public Single<DeviceResponse> deviceList() {
        return mApiDevice.deviceList(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),"false");
    }

    @Override
    public Single<DeviceResponse> deviceByPosition(int positionId) {
        return mApiDevice.deviceByPosition(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken())
                ,"false", positionId);
    }

    @Override
    public Single<BaseResponse> addDevice(Device device) {
        return mApiDevice.addDevice(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                device.getName(), device.getCode(), device.getPositionId());
    }

    @Override
    public Single<BaseResponse> editDevice(Device device) {
        return mApiDevice.editDevice(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                device.getId(), device.getName(), device.getPositionId());
    }

    @Override
    public Single<BaseResponse> deleteDevice(int[] arrayId) {
        Gson gson = new Gson();
        String id = gson.toJson(arrayId);
        return mApiDevice.deleteGroup(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                id);
    }
}
