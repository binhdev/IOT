package com.smarthome.iot.data.model.io;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IOCondition implements Serializable {

    @SerializedName("order")
    @Expose
    public Integer order;

    @SerializedName("condition_compare")
    @Expose
    public Integer conditionCompare;

    @SerializedName("io_id")
    @Expose
    public Integer ioId;

    @SerializedName("value_compare")
    @Expose
    public Integer valueCompare;

    @SerializedName("value_type")
    @Expose
    public Integer valueType;

    @SerializedName("value")
    @Expose
    public Object value;

    public IOCondition(Integer order, Integer conditionCompare, Integer ioId, Integer valueCompare, Integer valueType, Object value) {
        this.order = order;
        this.conditionCompare = conditionCompare;
        this.ioId = ioId;
        this.valueCompare = valueCompare;
        this.valueType = valueType;
        this.value = value;
    }

}