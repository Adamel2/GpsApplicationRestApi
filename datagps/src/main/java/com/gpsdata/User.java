package com.gpsdata;

import java.util.Date;

public class User {

    private String id ;
    private String fullName;
    private String userName;
    private String password;
    private boolean isActive;
    private long joinedAt;

    public User(String fullName, String userName,String password, boolean isActive, long joinedAt) {
        this.fullName = fullName;
        this.userName = userName;
        this.isActive = isActive;
        this.joinedAt = joinedAt;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(long joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", joinedAt=" + joinedAt +
                '}';
    }
}



