package com.projectgps.demo.controllers;

import com.projectgps.demo.Manger.LocationRequestManger;
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.projectgps.demo.gpsdata.Settings;
import com.projectgps.demo.gpsdata.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *
 * @author adham
 */

@CrossOrigin
@RestController
@RequestMapping("/setData")
public class ControllerPost {


    private UserManger userManger = new UserManger();
    private LocationRequestManger locationRequestManager = new LocationRequestManger();
    @PostMapping(path = "/addUser")
    public void setUser(@RequestBody User user) throws IOException {

        System.out.println(user.toString());
        userManger.insertUserIntoDynamoDB(user);


    }

    @PostMapping(path ="/addLocationRequest")
    public void setLocationRequest(@RequestBody LocationRequest locationRequest) throws IOException {

        System.out.println(locationRequest.toString());
        locationRequestManager.insertLocationRequestIntoDynamoDB(locationRequest);

    }

    @PostMapping(path = "/addSettings")
    public void setSettings(@RequestBody Settings settings){


        //insert Settings into database


    }
}
