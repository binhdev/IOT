package com.smarthome.iot.data.model;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device extends BaseModel implements Serializable
{
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("seriala_number_code")
    @Expose
    private String serialaNumberCode;

    @SerializedName("group_id")
    @Expose
    private Object groupId;

    @SerializedName("group_name")
    @Expose
    private Object groupName;

    @SerializedName("device_type_name")
    @Expose
    private String deviceTypeName;

    @SerializedName("has_io_input")
    @Expose
    private Boolean hasIoInput;

    @SerializedName("has_io_output")
    @Expose
    private Boolean hasIoOutput;

    private final static long serialVersionUID = -2833965300949966005L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSerialaNumberCode() {
        return serialaNumberCode;
    }

    public void setSerialaNumberCode(String serialaNumberCode) {
        this.serialaNumberCode = serialaNumberCode;
    }

    public Object getGroupId() {
        return groupId;
    }

    public void setGroupId(Object groupId) {
        this.groupId = groupId;
    }

    public Object getGroupName() {
        return groupName;
    }

    public void setGroupName(Object groupName) {
        this.groupName = groupName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Boolean getHasIoInput() {
        return hasIoInput;
    }

    public void setHasIoInput(Boolean hasIoInput) {
        this.hasIoInput = hasIoInput;
    }

    public Boolean getHasIoOutput() {
        return hasIoOutput;
    }

    public void setHasIoOutput(Boolean hasIoOutput) {
        this.hasIoOutput = hasIoOutput;
    }
}