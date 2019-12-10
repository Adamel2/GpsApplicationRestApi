package com.projectgps.demo.Manger;

import com.projectgps.demo.gpsdata.User;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.projectgps.demo.repository.UserRepository;

import java.io.IOException;
import java.util.List;
/**
 * @author adhameldda
 */

public class UserManger {
    private UserRepository repository=new UserRepository();

    public String insertUserIntoDynamoDB(User info) throws IOException, InvalidFormatException {
        repository.insertUserIntoDynamoDB(info);
        return "Successfully inserted into DynamoDB table";
    }

    public User getOneUserDetails( String id, String userName) {
        User iteam = repository.getOneUserDetails(id, userName);
        return iteam;
    }

    public User getOneUserDetails( String id) {
        User iteam = repository.getOneUserDetails(id);
        return iteam;
    }

    public User getUser(String userName, String password) {
        System.out.println("user : "  + userName);
        List<User> iteam = repository.getAllIteams();
        for (User a:iteam
        ) {if(a.getPassword().equals(password)&&a.getUserName().equals(userName)) {
            System.out.println("User is : "  +a );
            return a;
        }
        }
        return null;
    }
    public List<User> getAllIteams(){
        return repository.getAllIteams();
    }
}