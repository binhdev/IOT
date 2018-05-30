package com.smarthome.iot.data.source.remote.response.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.model.Device;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

import java.util.List;

public class DeviceResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Data> deviceResponseList;

    public List<Data> getDeviceResponseList(){
        return deviceResponseList;
    }

    public class Data extends Device{

        @SerializedName("position_data")
        @Expose
        private PositionData positionData;

        @SerializedName("o_digital")
        @Expose
        private List<ODigital> digitalList;
    }

    public class PositionData{

        @SerializedName("position_id")
        @Expose
        private int positionId;

        @SerializedName("position_name")
        @Expose
        private String positionName;

        public int getPositionId() {
            return positionId;
        }

        public void setPositionId(int positionId) {
            this.positionId = positionId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }
    }

    public class ODigital{

        @SerializedName("io_id")
        @Expose
        private int ioId;

        @SerializedName("current_value")
        @Expose
        private int currentValue;

        public int getIoId() {
            return ioId;
        }

        public void setIoId(int ioId) {
            this.ioId = ioId;
        }

        public int getCurrentValue() {
            return currentValue;
        }

        public void setCurrentValue(int currentValue) {
            this.currentValue = currentValue;
        }

    }
}
