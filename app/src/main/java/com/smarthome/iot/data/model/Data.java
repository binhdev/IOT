package com.smarthome.iot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("agent_id")
    @Expose
    private Integer agentId;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("birthday")
    @Expose
    private Object birthday;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("company_name")
    @Expose
    private String companyName;

    @SerializedName("type")
    @Expose
    private Integer type;

    @SerializedName("role_name")
    @Expose
    private String roleName;

    @SerializedName("root_position_id")
    @Expose
    private String rootPositionId;

    @SerializedName("agent_uuid")
    @Expose
    private String agentUuid;

    @SerializedName("token")
    @Expose
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRootPositionId() {
        return rootPositionId;
    }

    public void setRootPositionId(String rootPositionId) {
        this.rootPositionId = rootPositionId;
    }

    public String getAgentUuid() {
        return agentUuid;
    }

    public void setAgentUuid(String agentUuid) {
        this.agentUuid = agentUuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}