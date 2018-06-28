package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.source.DeviceDataSource;
import com.smarthome.iot.data.source.IODataSource;
import com.smarthome.iot.data.source.remote.api.ApiDevice;
import com.smarthome.iot.data.source.remote.api.ApiIO;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.DeviceResponse;
import com.smarthome.iot.data.source.remote.response.IOResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class IORemoteDataSource implements IODataSource.RemoteDataSource {

    private static IORemoteDataSource instance;

    private ApiIO mApiIO;

    private Context context;

    public IORemoteDataSource(ApiIO apiIO){
        this.mApiIO = apiIO;
    }

    public static synchronized IORemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new IORemoteDataSource(AppServiceClient.getIORemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<IOResponse> ioByDevice(int deviceId, int type) {
        return mApiIO.ioByDevice(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                "false", deviceId, type);
    }
}
