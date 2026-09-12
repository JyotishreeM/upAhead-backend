package com.upahead.backend.dto;

import com.upahead.backend.entity.User;

public class UserResponse {

    private Long userId;
    private String userName;
    private String emailId;
    private String state;
    private String city;
    private String address;
    private Boolean isActive;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.emailId = user.getEmailId();
        this.state = user.getState();
        this.city = user.getCity();
        this.address = user.getAddress();
        this.isActive = user.getIsActive();
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}