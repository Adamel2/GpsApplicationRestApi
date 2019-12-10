package com.projectgps.demo.controllers;
/**
 *
 * @author Eldda adham
 */
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/Login")
public class Login {

    private UserManger userManger  ;

    @PostMapping("/login")
    public User checkUserIntoDataBase(@RequestBody User user) throws IOException, JSONException {

//        System.out.println(user);
        userManger = new UserManger();
        return userManger.getUser(user.getUserName(),user.getPassword());
    }


}
