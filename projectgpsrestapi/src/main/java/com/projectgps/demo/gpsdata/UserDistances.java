package com.projectgps.demo.gpsdata;

import java.util.ArrayList;
import java.util.List;
/**
 * @author adhameldda
 */

public class UserDistances {
    private User user;
    private Double distance;

    public UserDistances(User user, Double distance) {
        this.user = user;
        this.distance = distance;
    }






    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "UserDistances{" +
                "user=" + user.toString() +
                ", distance=" + distance +
                '}';
    }
}
