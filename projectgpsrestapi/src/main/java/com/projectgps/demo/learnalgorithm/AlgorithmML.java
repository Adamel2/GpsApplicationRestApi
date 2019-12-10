/**
 *
 * @author Eldda adham
 */
package com.projectgps.demo.learnalgorithm;


import com.projectgps.demo.Manger.LocationRequestManger;
import com.projectgps.demo.Manger.SettingsManger;
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.projectgps.demo.gpsdata.Settings;
import com.projectgps.demo.gpsdata.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adhameldda
 */

public class AlgorithmML {

    private UserManger userManger;
    private SettingsManger settingsManger;
    private LocationRequestManger locationRequestManger;
    private List<ArrayList<LocationRequest>> listRout ;


    private HashMap<User,List<LocationRequest>> listHashMap;

    public AlgorithmML( ){
        this.listHashMap =new  HashMap<User,List<LocationRequest>>();
        this.userManger = new UserManger();
        this.settingsManger = new SettingsManger();
        this.locationRequestManger = new LocationRequestManger();
    }

    public  void LearnAlgorithm(){


        while(true){

            this.listHashMap =new  HashMap<User,List<LocationRequest>>();
            List<User> users = userManger.getAllIteams();
            List<LocationRequest> locations = locationRequestManger.getAllIteams();
            List<Settings> settings = settingsManger.getAllIteams();
            listRout = new ArrayList<ArrayList<LocationRequest>>();
            ArrayList<LocationRequest> requestList = new ArrayList<>();
            ArrayList<LocationRequest> locationRequests = new ArrayList<LocationRequest>();



                for (User user: users
                     ) {

                    for (LocationRequest locationRequest: locations
                         ) {
                        if (locationRequest.getUserId().equals(user.getId())) {
                            locationRequests.add(locationRequest);
                        }
                    }
                    listHashMap.put(user,(ArrayList)locationRequests.clone());
                    locationRequests.clear();
                }

                for (Map.Entry<User,List<LocationRequest>> entry : listHashMap.entrySet()) {

                    for (int i = 0; i <entry.getValue().size()-1 ; i++) {

                        if ((getDifferentTime(entry.getValue().get(i).getTime(),entry.getValue().get(i+1).getTime())>=0) &&
                                ( getDifferentTime(entry.getValue().get(i).getTime(),entry.getValue().get(i+1).getTime())<=59)){
                            requestList.add(entry.getValue().get(i));
                        }
                        if (( getDifferentTime(entry.getValue().get(i).getTime(),entry.getValue().get(i+1).getTime())>59)){
                            requestList.add(entry.getValue().get(i));
                            listRout.add((ArrayList)requestList.clone());
                            requestList.clear();
                        }
                        if(entry.getValue().size()-2 == i){
                            requestList.add(entry.getValue().get(i));
                            requestList.add(entry.getValue().get(i+1));
                            listRout.add((ArrayList)requestList.clone());
                            requestList.clear();

                        }
                    }
                    
                }




        }

    }
    public Long getDifferentTime(long time, long time1) {
        Long timeInMin = (time)/ (60*1000)%60;
        Long timeInMinLastLocation = (time1)/ (60*1000)%60;
        if(timeInMin >= timeInMinLastLocation)
            return timeInMin - timeInMinLastLocation;
        return timeInMinLastLocation - timeInMin;
    }

}
