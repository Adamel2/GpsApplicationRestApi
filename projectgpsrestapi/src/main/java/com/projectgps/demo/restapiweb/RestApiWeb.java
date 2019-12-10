package com.projectgps.demo.restapiweb;

import com.projectgps.demo.Manger.LocationRequestManger;
import com.projectgps.demo.Manger.SettingsManger;
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.projectgps.demo.gpsdata.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adhameldda
 */

@CrossOrigin
@RestController
@RequestMapping("/web")
public class RestApiWeb {

    private UserManger userManger;
    private SettingsManger settingsManger;
    private LocationRequestManger locationRequestManger;


    public RestApiWeb() {
        this.userManger = new UserManger();
        this.settingsManger = new SettingsManger();
        this.locationRequestManger = new LocationRequestManger();
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){

        return userManger.getAllIteams();
    }
    @GetMapping("/id/{id}")
    public List<LocationRequest> getLocationById(@PathVariable String id){

        List<LocationRequest> list = this.locationRequestManger.getAllIteams();
        List<LocationRequest> locations = new ArrayList<>();

        for (LocationRequest locationRequest: list
             ) {

            if (locationRequest.getUserId().equals(id)) {
                locations.add(locationRequest);
            }
        }

        return locations;
     }

     @GetMapping("/getSizeUsers")
     public int getSizeUsers(){

        return this.userManger.getAllIteams().size();
     }
}
