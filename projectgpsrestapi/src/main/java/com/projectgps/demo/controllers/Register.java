package com.projectgps.demo.controllers;

/**
 *
 * @author Eldda adham
 */
import com.projectgps.demo.Manger.UserManger;
import com.projectgps.demo.gpsdata.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class Register {

    private UserManger userManger  ;

    @PostMapping("/register")
    public String setUserInDataBase(@RequestBody User user) throws IOException {
        userManger = new UserManger();
        List<User> list = new ArrayList<>();
        list = userManger.getAllIteams();
        for (User u: list
             ) {
            if (u.getUserName().equals(user.getUserName())) {
                return "existing username";
            }
        }
        userManger.insertUserIntoDynamoDB(user);
        return "Successfully";
    }

}
