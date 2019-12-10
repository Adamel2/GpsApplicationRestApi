package com.projectgps.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.projectgps.demo.gpsdata.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adhameldda
 */

@Repository
public class UserRepository {


    private dynamodbConnect dynamodbConnect=new dynamodbConnect();
    private DynamoDBMapper mapper=new DynamoDBMapper(dynamodbConnect.amazonDynamoDBConfig());

    public void insertUserIntoDynamoDB(User info) {
        mapper.save(info);
    }
    public  User getOneUserDetails(String id, String userName) {
        return mapper.load(User.class, id, userName);
    }

    public  User getOneUserDetails(String id) {
        return mapper.load(User.class, id);
    }

    public List<User> getAllIteams() {
        List<User> scanResult = mapper.scan(User.class, new DynamoDBScanExpression());
        return scanResult;
    }

}