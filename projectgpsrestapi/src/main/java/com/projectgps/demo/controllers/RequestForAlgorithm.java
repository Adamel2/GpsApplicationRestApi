package com.projectgps.demo.controllers;

import com.projectgps.demo.algorithmnaive.AlgorithmNaive;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.projectgps.demo.gpsdata.User;
import com.projectgps.demo.gpsdata.UserDistances;
import com.projectgps.demo.testgpsalgorithm.AdvancedAlgorithmWithML;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author  Adham
 */
@CrossOrigin
@RestController
@RequestMapping("sendRequestForCheck")
public class RequestForAlgorithm {


    private AlgorithmNaive algorithmNaive; // Created By Ayman
    private AdvancedAlgorithmWithML advancedAlgorithmWithML; // Created By Adham


    public RequestForAlgorithm() throws IOException, JSONException {

    }

    /**
     * @author  Ayman
     */
    @PostMapping("/sendRequest")
    public List<UserDistances> sendRequest(@RequestBody User user) throws IOException, JSONException {
        this.algorithmNaive = new AlgorithmNaive();
        List<UserDistances> usersWithDistance = algorithmNaive.getAllNearbyUsersByGivenUsername(user.getId());
        return usersWithDistance;
    }




    /**
     * @author  Adham
     */
    @PostMapping("/getUsersNearby")
    public List<User> getUsers(@RequestBody User user) {

        advancedAlgorithmWithML = new AdvancedAlgorithmWithML();
        advancedAlgorithmWithML.algorithm(user.getId());
        return advancedAlgorithmWithML.getUsers();
    }
    @PostMapping("/getLocationsNearby")
    public List<LocationRequest> getLocations(@RequestBody User user) {

        advancedAlgorithmWithML = new AdvancedAlgorithmWithML();
        advancedAlgorithmWithML.algorithm(user.getId());
        return advancedAlgorithmWithML.getLocations();
    }
    @PostMapping("/getUsersNearbyWithDistances")
    public List<UserDistances> getUsersWithDistances(@RequestBody User user) {

        advancedAlgorithmWithML = new AdvancedAlgorithmWithML();
        advancedAlgorithmWithML.algorithm(user.getId());
        return advancedAlgorithmWithML.getUsersWithDistance();
    }
}