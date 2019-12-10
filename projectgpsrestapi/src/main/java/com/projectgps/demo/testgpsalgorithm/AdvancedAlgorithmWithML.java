package com.projectgps.demo.testgpsalgorithm;


/**
 *
 * @   Eldda Adham
 */

import com.projectgps.demo.Manger.LocationRequestManger;
import com.projectgps.demo.Manger.SettingsManger;
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.projectgps.demo.gpsdata.User;
import com.projectgps.demo.gpsdata.UserDistances;

import java.util.*;

public class AdvancedAlgorithmWithML {

    private UserManger userManger;
    private LocationRequestManger locationRequestManger;
    private SettingsManger settingsManger;
    private List<LocationRequest> resultLocations;
    private List<User> resultUsers;
    private List<UserDistances> usersWithDistance;
    private final int DISTANCE=4;

    public AdvancedAlgorithmWithML() {
        this.userManger = new UserManger();
        this.locationRequestManger = new LocationRequestManger();
        this.settingsManger = new SettingsManger();
    }


    public void algorithm(String userId) {

        List<LocationRequest> locations = locationRequestManger.getAllIteams();
        ArrayList<LocationRequest> locationRequests = new ArrayList<LocationRequest>();
        List<User> users = userManger.getAllIteams();
        HashMap<User, List<LocationRequest>> listHashMap = new HashMap<User, List<LocationRequest>>();
        resultLocations = new ArrayList<LocationRequest>();
        resultUsers = new ArrayList<User>();

        usersWithDistance = new ArrayList<UserDistances>();

        List<LocationRequest> userListFoundLocations = new ArrayList<LocationRequest>();
        List<LocationRequest> userCurrentLocations = new ArrayList<LocationRequest>();

        for (User user : users
        ) {

            for (LocationRequest locationRequest : locations
            ) {
                if (locationRequest.getUserId().equals(user.getId())) {
                    locationRequests.add(locationRequest);
                }
            }
            listHashMap.put(user, (ArrayList) locationRequests.clone());
            locationRequests.clear();
        }

        for (Map.Entry<User, List<LocationRequest>> entry : listHashMap.entrySet()) {

            Collections.sort(entry.getValue());

        }
        for (Map.Entry<User, List<LocationRequest>> entry : listHashMap.entrySet()) {

            if (!entry.getKey().getId().equals(userId) && !entry.getValue().isEmpty()) {
                userListFoundLocations.add(entry.getValue().get(entry.getValue().size() - 1));
            }
            if (entry.getKey().getId().equals(userId)) {

                userCurrentLocations.add(entry.getValue().get(entry.getValue().size() - 1));

            }
        }

        for (int i = 0; i < userListFoundLocations.size(); i++) {

            if (distanceKm(userListFoundLocations.get(i).getLatitude(), userListFoundLocations.get(i).getLongitude(),
                    userCurrentLocations.get(0).getLatitude(), userCurrentLocations.get(0).getLongitude(), "K") < DISTANCE) {
                resultLocations.add(userListFoundLocations.get(i));
                resultUsers.add(userManger.getOneUserDetails(userListFoundLocations.get(i).getUserId()));
                usersWithDistance.add(new UserDistances(userManger.getOneUserDetails(userListFoundLocations.get(i).getUserId()),distanceKm(userListFoundLocations.get(i).getLatitude(), userListFoundLocations.get(i).getLongitude(),
                        userCurrentLocations.get(0).getLatitude(), userCurrentLocations.get(0).getLongitude(), "K")));
            }

        }

    }


    public double distanceKm(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0.0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit == "K") {
                dist = dist * 1.609344;
            } else if (unit == "N") {
                dist = dist * 0.8684;
            }
            return (dist);
        }

    }

    public List<LocationRequest> getLocations(){

        return this.resultLocations;
    }
    public List<User> getUsers(){

        return this.resultUsers;
    }
    public List<UserDistances> getUsersWithDistance(){

        return usersWithDistance;
    }
}
