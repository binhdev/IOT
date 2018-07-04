package com.smarthome.iot.data.model.io;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IOAction implements Serializable {

    @SerializedName("io_id")
    @Expose
    public Integer ioId;

    @SerializedName("value")
    @Expose
    public Object value;

    public IOAction(Integer ioId, Object value) {
        this.ioId = ioId;
        this.value = value;
    }

}