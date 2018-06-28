package com.smarthome.iot.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IOCondition implements Serializable {

    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("io_id")
    @Expose
    private Integer ioId;
    @SerializedName("value_compare")
    @Expose
    private Integer valueCompare;
    @SerializedName("value_type")
    @Expose
    private Integer valueType;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("condition_compare")
    @Expose
    private Integer conditionCompare;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getIoId() {
        return ioId;
    }

    public void setIoId(Integer ioId) {
        this.ioId = ioId;
    }

    public Integer getValueCompare() {
        return valueCompare;
    }

    public void setValueCompare(Integer valueCompare) {
        this.valueCompare = valueCompare;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getConditionCompare() {
        return conditionCompare;
    }

    public void setConditionCompare(Integer conditionCompare) {
        this.conditionCompare = conditionCompare;
    }

}