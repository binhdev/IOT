package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.data.model.Pagination;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

import java.util.List;

public class ListGroupResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    public class Data {
        @SerializedName("pagination")
        @Expose
        private Pagination pagination;

        @SerializedName("items")
        @Expose
        private List<Group> groups;

        public List<Group> getGroups(){
            return groups;
        }
    }

    public Data getData(){
        return data;
    }
}
