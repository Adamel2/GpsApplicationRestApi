package com.projectgps.demo.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.projectgps.demo.gpsdata.Settings;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author adhameldda
 */

@Repository
public class SettingsRepository {

    private dynamodbConnect dynamodbConnect=new dynamodbConnect();
    private DynamoDBMapper mapper=new DynamoDBMapper(dynamodbConnect.amazonDynamoDBConfig());


    public void insertSettingsIntoDynamoDB(Settings info) {
        mapper.save(info);
    }
    public  Settings getValueDetails(String name,String id) {
        return mapper.load(Settings.class,id,name);
    }
    public List<Settings> getAllIteams() {
        List<Settings> scanResult = mapper.scan(Settings.class, new DynamoDBScanExpression());
        return scanResult;
    }




}
