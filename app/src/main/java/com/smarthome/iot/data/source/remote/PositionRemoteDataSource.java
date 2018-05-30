package com.smarthome.iot.data.source.remote;

import android.content.Context;

import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.source.PositionDataSource;
import com.smarthome.iot.data.source.remote.api.ApiPosition;
import com.smarthome.iot.data.source.remote.response.BaseResponse;
import com.smarthome.iot.data.source.remote.response.position.PositionResponse;
import com.smarthome.iot.data.source.remote.service.AppServiceClient;
import com.smarthome.iot.utils.AppConstants;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.helper.StringHelper;

import io.reactivex.Single;

public class PositionRemoteDataSource implements PositionDataSource.RemoteDataSource {

    private static PositionRemoteDataSource instance;

    private ApiPosition mApiPosition;

    private Context context;

    public PositionRemoteDataSource(ApiPosition mApiPosition){
        this.mApiPosition = mApiPosition;
    }

    public static synchronized PositionRemoteDataSource getInstance(Context context) {
        context = context;
        if(instance == null){
            instance = new PositionRemoteDataSource(AppServiceClient.getPositionRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<BaseResponse> createPosition(Position position) {
        return mApiPosition.createPostion(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                position.getName(), position.getDescription(), position.getParentId());
    }

    @Override
    public Single<BaseResponse> editPosition(Position position) {
        return mApiPosition.editPosition(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,
                AppPrefs.getInstance(context).getApiAccessToken()),
                position.getId(), position.getName(), position.getDescription(), position.getParentId());
    }

    @Override
    public Single<BaseResponse> deletePosition(int[] ids) {
        return mApiPosition.deletePosition(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                ids);
    }

    @Override
    public Single<PositionResponse> positionTreeStructure() {
        return mApiPosition.positionTreeStructure(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken())
        , "false");
    }

    @Override
    public Single<PositionResponse> allPosition() {
        return mApiPosition.allPosition(StringHelper.ConcatString(AppConstants.SCHEMA_BEARER,AppPrefs.getInstance(context).getApiAccessToken()),
                "false");
    }
}