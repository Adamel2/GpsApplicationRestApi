package com.projectgps.demo.algorithmnaive;

import com.projectgps.demo.Manger.LocationRequestManger;
import com.projectgps.demo.Manger.SettingsManger;
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.controllers.*;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.projectgps.demo.gpsdata.User;
import com.projectgps.demo.gpsdata.UserDistances;
import org.apache.poi.ss.formula.functions.T;
import org.joda.time.DateTime;
import org.json.JSONException;

import java.io.IOException;
import java.sql.Time;
import java.util.*;
/**
 * @author adhameldda
 */

public class AlgorithmNaive {

    private UserManger userManger;
    private LocationRequestManger locationRequestManger;
    private SettingsManger settingsManger;
    private List<LocationRequest> locationRequests;
    private List<User> users;

    public AlgorithmNaive() throws IOException, JSONException {

        userManger = new UserManger();
        locationRequestManger = new LocationRequestManger();
        settingsManger = new SettingsManger();
        this.users = new ArrayList<>();
        this.locationRequests = this.locationRequestManger.getAllIteams();
        users = this.userManger.getAllIteams();
    }

    public List<UserDistances> getAllNearbyUsersByGivenUsername(String userID) {

        LocationRequest lastLocation = getLastLocationByUserId(userID);
        Double dis;
        System.out.println("My Location relative is : "  +lastLocation.getUserId() +
                " , " + lastLocation.getLatitude() + " , " + lastLocation.getLongitude() +  " , "  + new Date(lastLocation.getTime()));
        List<LocationRequest> locationRequests = new ArrayList<>();
        if(this.locationRequests == null || !(this.locationRequests.size() > 0)) {
            System.out.println("no entries");
            return null;
        }
        for (int i = 0; i < this.locationRequests.size(); i++) {
            if (!userID.equals(this.locationRequests.get(i).getUserId())) {
                dis =getDistanceFromLatLonInKm(lastLocation.getLatitude(), lastLocation.getLongitude(), this.locationRequests.get(i).getLatitude(),
                        this.locationRequests.get(i).getLongitude());
                if (dis <= 100
                        && getDifferentTimeInMin(this.locationRequests.get(i).getTime(), lastLocation.getTime()) < 100){
                    locationRequests.add(this.locationRequests.get(i));

                }
            }
        }
        if (locationRequests == null || !(locationRequests.size() > 0)) {
            System.out.println("no such near body");
            return null;
        }
        List<LocationRequest> lastLocationsByEveryUser =  getAllLastLocationsOneForEveryUser(locationRequests);
        System.out.println("--------------done-----------");
        return getAllUserDistanceNearby(lastLocationsByEveryUser, lastLocation);
    }

    private List<LocationRequest> getAllLastLocationsOneForEveryUser(List<LocationRequest> locationRequests) {

        HashMap<String, List<LocationRequest>> locationsRequestNew = new HashMap<>();
        List<LocationRequest> perUser;
        for(int i = 0; i < locationRequests.size(); i++)
        {
            perUser = locationsRequestNew.get(locationRequests.get(i).getUserId());

            if(perUser == null || !(perUser.size() > 0))
            {
                perUser = new ArrayList<>();
                perUser.add(locationRequests.get(i));
                locationsRequestNew.put(locationRequests.get(i).getUserId() , perUser);
            }
            else{
                perUser.add(locationRequests.get(i));
                locationsRequestNew.put(locationRequests.get(i).getUserId() , perUser);
            }
            perUser = new ArrayList<>();
        }
         return getAllLastLocationsOneForEveryUserHashMap(locationsRequestNew);
    }

    private List<LocationRequest> getAllLastLocationsOneForEveryUserHashMap(HashMap<String, List<LocationRequest>> locationsRequestNew) {

        List<LocationRequest> list = new ArrayList<>();
        Date time;
        int index = 0;
        for(int i = 0; i < this.users.size(); i++) {
            List<LocationRequest> userRequestLocation = locationsRequestNew.get(this.users.get(i).getId());
            if (!(userRequestLocation == null || !(userRequestLocation.size() > 0))) {
                System.out.println("The size of the Request Location of this user is : " + userRequestLocation.size());
                time = new Date(userRequestLocation.get(0).getTime());
                for (int j = 0; j < userRequestLocation.size(); j++) {
                    if (time.before(new Date(userRequestLocation.get(j).getTime()))) {
                        time = (Date) new Date(userRequestLocation.get(j).getTime()).clone();
                        index = j;
                    }
                    System.out.println("ID: " + userRequestLocation.get(j).getUserId() + " , lat1: " + userRequestLocation.get(j).getLatitude()
                            + " , long1: " + userRequestLocation.get(j).getLongitude() + " , Time: "  + time);
                }
                list.add(userRequestLocation.get(index));
                System.out.println("last chosen date is: "  + new Date(userRequestLocation.get(index).getTime()));
                System.out.println("------------------------------------------------------");
            }
        }
        return list;
    }

    private Long getDifferentTimeInMin(long time, long time1) {
        Long timeInMin = (time) / (60 * 1000) % 60;
        Long timeInMinLastLocation = (time1) / (60 * 1000) % 60;
        if (timeInMin >= timeInMinLastLocation)
            return timeInMin - timeInMinLastLocation;
        return timeInMinLastLocation - timeInMin;
    }

    private LocationRequest getLastLocationByUserId(String id) {
        LocationRequest locationRequest = new LocationRequest();
        Date time = new Date(this.locationRequests.get(0).getTime());
        int index = 0;
        for (int i = 0; i < this.locationRequests.size(); i++) {
            if (this.locationRequests.get(i).getUserId().equals(id)) {
                if(time.before(new Date(this.locationRequests.get(i).getTime()))) {
                    time = (Date) new Date(this.locationRequests.get(i).getTime()).clone();
                    index = i;
                }
            }
        }
        locationRequest = locationRequests.get(index);
        return locationRequest;
    }

    private String getUserId(String userName) {

        for (int i = 0; i < this.users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)) {
                return users.get(i).getId();
            }
        }
        return null;
    }

    public static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;

            return (dist);
        }
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI / 180);


    }

    public List<UserDistances> getAllUserDistanceNearby(List<LocationRequest> locationRequests, LocationRequest locationRequest) {

        double distance=0;
        List<UserDistances> userDistances = new ArrayList<>();
        System.out.println("the size of Distinct RequestLocation of all is: " + locationRequests.size());
        for(int i = 0; i < locationRequests.size(); i++)
        {
            distance = getDistanceFromLatLonInKm(locationRequests.get(i).getLatitude(), locationRequests.get(i).getLongitude(),
                    locationRequest.getLatitude(), locationRequest.getLongitude());
            userDistances.add(new UserDistances(getUserById(locationRequests.get(i).getUserId()), distance));
            System.out.println("lat1: " + locationRequests.get(i).getLatitude()
                    + " , long1: " + locationRequests.get(i).getLongitude() +
                            " , At Time: "  + new Date(locationRequests.get(i).getTime()) +
                    " , lat2: " + locationRequest.getLatitude()
                    + " , long2: " + locationRequest.getLongitude() +  " : distance is: "  + distance
                    + " , At Time: "  + new Date(locationRequest.getTime()));
        }
        return userDistances;
    }

    private User getUserById(String userId) {
        for(int i = 0; i < this.users.size(); i++)
        {
            if(this.users.get(i).getId().equals(userId))
                return this.users.get(i);
        }
        return null;
    }


}