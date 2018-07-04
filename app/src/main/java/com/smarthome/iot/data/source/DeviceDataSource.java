package com.smarthome.iot.data.source;

import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.DeviceResponse;

import io.reactivex.Single;

public interface DeviceDataSource {

    interface LocalDataSource{
    }

    interface RemoteDataSource{

        Single<DeviceResponse> deviceList();

        Single<DeviceResponse> deviceByPosition(int positionId);

        Single<DeviceResponse> deviceByPositionWithDataTypeIO(int positionId, int dataTypeIO);

        Single<BaseResponse> addDevice(Device device);

        Single<BaseResponse> editDevice(Device device);

        Single<BaseResponse> deleteDevice(int[] arrayId);
    }
}
