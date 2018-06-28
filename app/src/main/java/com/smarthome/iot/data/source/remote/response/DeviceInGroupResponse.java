package com.smarthome.iot.data.source.remote.response;
import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.model.Pagination;

public class DeviceInGroupResponse {
    public class Data implements Serializable
    {
        @SerializedName("devices")
        @Expose
        private Devices devices;

        @SerializedName("group")
        @Expose
        private Group group;
        private final static long serialVersionUID = -3008593159048410507L;

        public Devices getDevices() {
            return devices;
        }

        public void setDevices(Devices devices) {
            this.devices = devices;
        }

        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }

    }

    public class Devices implements Serializable
    {

        @SerializedName("pagination")
        @Expose
        private Pagination pagination;
        @SerializedName("items")
        @Expose
        private List<Item> items = null;
        private final static long serialVersionUID = -4894577295971516965L;

        public Pagination getPagination() {
            return pagination;
        }

        public void setPagination(Pagination pagination) {
            this.pagination = pagination;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

    }

    public class Item implements Serializable
    {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("position_id")
        @Expose
        private Integer positionId;
        @SerializedName("device_code")
        @Expose
        private String deviceCode;
        @SerializedName("position_name")
        @Expose
        private String positionName;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        private final static long serialVersionUID = 2977420073143578499L;

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

        public Integer getPositionId() {
            return positionId;
        }

        public void setPositionId(Integer positionId) {
            this.positionId = positionId;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }
}
