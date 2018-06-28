package com.smarthome.iot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IOAction implements Serializable {

    @SerializedName("io_id")
    @Expose
    private Integer ioId;
    @SerializedName("value")
    @Expose
    private Integer value;

    public Integer getIoId() {
        return ioId;
    }

    public void setIoId(Integer ioId) {
        this.ioId = ioId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}