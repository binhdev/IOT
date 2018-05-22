package com.smarthome.iot.data.source.local;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.model.User;
import com.smarthome.iot.data.source.GroupDataSource;
import com.smarthome.iot.data.source.PositionDataSource;

import io.reactivex.Observable;

public class PositionLocalDataSource implements PositionDataSource.LocalDataSource {

    private static PositionLocalDataSource instance;

    public PositionLocalDataSource(){
    }

    public static synchronized PositionLocalDataSource getInstance() {
        if (instance == null) {
            instance = new PositionLocalDataSource();
        }
        return instance;
    }

}
