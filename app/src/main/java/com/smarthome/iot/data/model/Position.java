package com.smarthome.iot.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Position implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("parent_id")
    @Expose
    private Integer parentId;

    @SerializedName("has_io_input")
    @Expose
    private Boolean hasIoInput;

    @SerializedName("has_io_output")
    @Expose
    private Boolean hasIoOutput;

    @SerializedName("child")
    @Expose
    private List<Position> child;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public List<Position> getChild() {
        return child;
    }
}