package com.smarthome.iot.data.model;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device implements Serializable
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

    @SerializedName("position_id")
    @Expose
    private Integer positionId;

    @SerializedName("position_name")
    @Expose
    private String positionName;

    @SerializedName("group_id")
    @Expose
    private int groupId;

    @SerializedName("group_name")
    @Expose
    private String groupName;

    @SerializedName("device_type_name")
    @Expose
    private String deviceTypeName;

    @SerializedName("serial_number_id")
    @Expose
    private Integer serialNumberId;

    @SerializedName("has_io_input")
    @Expose
    private Boolean hasIoInput;

    @SerializedName("has_io_output")
    @Expose
    private Boolean hasIoOutput;

    @SerializedName("o_digital")
    @Expose
    private Object oDigital;

    @SerializedName("serial_number_code")
    @Expose
    private String serialNumberCode;
    private final static long serialVersionUID = -6116019743933638232L;

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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
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

    public Object getODigital() {
        return oDigital;
    }

    public void setODigital(Object oDigital) {
        this.oDigital = oDigital;
    }

    public String getSerialNumberCode() {
        return serialNumberCode;
    }

    public void setSerialNumberCode(String serialNumberCode) {
        this.serialNumberCode = serialNumberCode;
    }

}