package com.projectgps.demo.controllers;
/**
 *
 *
 */

import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author adham
 */
@CrossOrigin
@RestController
@RequestMapping("getData")
public class ControllerGet {


    private UserManger userManger = new UserManger();


    @GetMapping("/getUser")
    public User getUser(String id){

        return userManger.getOneUserDetails(id);
    }
    @GetMapping("/getUsers")
    public List<User> getUsers(){

        return userManger.getAllIteams();
    }





}