package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.source.IODataSource;
import com.smarthome.iot.data.source.remote.api.ApiIO;
import com.smarthome.iot.data.source.remote.response.IOResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class IORemoteDataSource implements IODataSource.RemoteDataSource {

    private static IORemoteDataSource instance;

    private ApiIO mApiIO;

    private static Context mContext;

    public IORemoteDataSource(ApiIO apiIO){
        this.mApiIO = apiIO;
    }

    public static synchronized IORemoteDataSource getInstance(Context context) {
        mContext = context;
        if(instance == null){
            instance = new IORemoteDataSource(AppServiceClient.getIORemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<IOResponse> ioByDevice(int deviceId, int type) {
        return mApiIO.ioByDevice(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(mContext).getApiAccessToken()),
                "false", deviceId, type);
    }

    @Override
    public Single<IOResponse> ioByDeviceWithDataTypeIO(int deviceId, int type, int dataTypeIO) {
        return mApiIO.ioByDeviceWithDataTypeIO(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(mContext).getApiAccessToken()),
                "false", deviceId, type, dataTypeIO);
    }
}
