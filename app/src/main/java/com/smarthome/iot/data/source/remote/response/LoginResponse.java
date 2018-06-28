package com.smarthome.iot.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthome.iot.data.source.remote.response.BaseResponse;

import java.io.Serializable;

public class LoginResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData(){
        return data;
    }

    public void setData(Data data){
        this.data = data;
    }


    public class Data implements Serializable
    {

        @SerializedName("token_type")
        @Expose
        private String tokenType;
        @SerializedName("expires_in")
        @Expose
        private Integer expiresIn;
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("refresh_token")
        @Expose
        private String refreshToken;
        private final static long serialVersionUID = 2453672161729376860L;

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(Integer expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

    }

}
