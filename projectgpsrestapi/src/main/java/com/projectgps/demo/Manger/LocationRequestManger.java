package com.projectgps.demo.Manger;
import com.projectgps.demo.gpsdata.LocationRequest;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.projectgps.demo.repository.LocationRequestRepository;

import java.io.IOException;
import java.util.List;

/**
 * @author adhameldda
 */

public class LocationRequestManger {
    private LocationRequestRepository repository=new LocationRequestRepository();


    public String insertLocationRequestIntoDynamoDB( LocationRequest info) throws IOException, InvalidFormatException {
        repository.insertLocationRequestIntoDynamoDB(info);
        return "Successfully inserted into DynamoDB table";
    }

    public LocationRequest getOneUserDetails( String userId) {
        List<LocationRequest> iteam = repository.getAllIteams();
        for (LocationRequest a:iteam) {if(a.getUserId().equals(userId))
            return  a; }
        return null;}

    public List<LocationRequest> getAllIteams(){
        return repository.getAllIteams();
    }

}

