package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.model.device.Device;
import com.smarthome.iot.data.model.io.IO;
import com.smarthome.iot.data.model.io.IOAction;
import com.smarthome.iot.data.model.io.IOCondition;

import java.util.List;

public class DetailAlarmResponse extends BaseResponse{

    @SerializedName("data")
    @Expose
    public Data data;

    public Data getData(){
        return data;
    }

    public class Data{

        @SerializedName("id")
        @Expose
        public int id;

        @SerializedName("name")
        @Expose
        public int name;

        @SerializedName("priority")
        @Expose
        public int priority;

        @SerializedName("agent_id")
        @Expose
        public int agentId;

        @SerializedName("condition")
        @Expose
        public List<Condition> conditionList;

        @SerializedName("action")
        @Expose
        public List<Action> actionList;
    }
    public class Condition extends IOCondition{

        public Condition(Integer order, Integer conditionCompare, Integer ioId, Integer valueCompare, Integer valueType, Object value) {
            super(order, conditionCompare, ioId, valueCompare, valueType, value);
        }

        @SerializedName("info_io")
        @Expose
        public InfoIO infoIO;

        @SerializedName("info_value")
        @Expose
        public InfoIO infoValue;
    }

    public class InfoIO{

        @SerializedName("position")
        @Expose
        public Position position;

        @SerializedName("device")
        @Expose
        public Device device;

        @SerializedName("io")
        @Expose
        public IO io;
    }

    public class Action extends IOAction{

        public Action(Integer ioId, Object value) {
            super(ioId, value);
        }

        @SerializedName("info_io")
        @Expose
        public InfoIO infoIO;
    }
}
