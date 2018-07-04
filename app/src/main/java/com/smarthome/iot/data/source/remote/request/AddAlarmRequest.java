package com.smarthome.iot.data.source.remote.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.io.IOAction;
import com.smarthome.iot.data.model.io.IOCondition;

public class AddAlarmRequest extends BaseRequest {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("notification")
    @Expose
    private Integer notification;

    @SerializedName("condition")
    @Expose
    private List<IOCondition> condition = null;

    @SerializedName("action")
    @Expose
    private List<IOAction> action = null;

    public AddAlarmRequest(String name, Integer priority, Integer notification, List<IOCondition> condition, List<IOAction> action) {
        this.name = name;
        this.priority = priority;
        this.notification = notification;
        this.condition = condition;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getNotification() {
        return notification;
    }

    public void setNotification(Integer notification) {
        this.notification = notification;
    }

    public List<IOCondition> getCondition() {
        return condition;
    }

    public void setCondition(List<IOCondition> condition) {
        this.condition = condition;
    }

    public List<IOAction> getAction() {
        return action;
    }

    public void setAction(List<IOAction> action) {
        this.action = action;
    }

}