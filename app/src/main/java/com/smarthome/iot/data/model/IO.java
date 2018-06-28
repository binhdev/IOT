package com.smarthome.iot.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IO implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("data_type")
    @Expose
    private Integer dataType;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("current_value")
    @Expose
    private Object currentValue;
    @SerializedName("data_multi_selection")
    @Expose
    private Object dataMultiSelection;
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;
    @SerializedName("device_name")
    @Expose
    private String deviceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Object currentValue) {
        this.currentValue = currentValue;
    }

    public Object getDataMultiSelection() {
        return dataMultiSelection;
    }

    public void setDataMultiSelection(Object dataMultiSelection) {
        this.dataMultiSelection = dataMultiSelection;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}