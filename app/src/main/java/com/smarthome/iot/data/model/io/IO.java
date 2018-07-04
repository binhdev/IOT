package com.smarthome.iot.data.model.io;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IO implements Serializable {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("code")
    @Expose
    public String code;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("type")
    @Expose
    public Integer type;

    @SerializedName("data_type")
    @Expose
    public Integer dataType;

    @SerializedName("status")
    @Expose
    public Integer status;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("current_value")
    @Expose
    public Object currentValue;

    @SerializedName("data_multi_selection")
    @Expose
    public List<DataMultiSelection> dataMultiSelection;

    @SerializedName("device_id")
    @Expose
    public Integer deviceId;

    @SerializedName("device_name")
    @Expose
    public String deviceName;

    public class DataMultiSelection {

        @SerializedName("order")
        @Expose
        public Integer order;

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("server")
        @Expose
        public String server;

        @SerializedName("config_1")
        @Expose
        public String config1;

        @SerializedName("config_2")
        @Expose
        public String config2;

        @SerializedName("config_3")
        @Expose
        public String config3;
    }

}