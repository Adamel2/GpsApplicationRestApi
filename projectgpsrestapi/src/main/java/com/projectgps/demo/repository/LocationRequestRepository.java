package com.projectgps.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.projectgps.demo.gpsdata.LocationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author adhameldda
 */

@Repository
public class LocationRequestRepository {


    private dynamodbConnect dynamodbConnect=new dynamodbConnect();;
    private DynamoDBMapper mapper=new DynamoDBMapper(dynamodbConnect.amazonDynamoDBConfig());;

    public void insertLocationRequestIntoDynamoDB(LocationRequest info) {
        mapper.save(info);
    }
    public  LocationRequest getOneUserDetails(String id) {
        return mapper.load(LocationRequest.class, id); }

    public List<LocationRequest> getAllIteams() {
        List<LocationRequest> scanResult = mapper.scan(LocationRequest.class, new DynamoDBScanExpression());
        return scanResult;
    }

}
