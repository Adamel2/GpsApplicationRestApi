package com.projectgps.demo.Manger;

import com.projectgps.demo.gpsdata.Settings;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.projectgps.demo.repository.SettingsRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
/**
 * @author adhameldda
 */

@Component
public class SettingsManger {

    private SettingsRepository repository=new SettingsRepository();




    public String insertSettingsIntoDynamoDB( Settings info) throws IOException, InvalidFormatException {
        repository.insertSettingsIntoDynamoDB(info);
        return "Successfully inserted into DynamoDB table";
    }


    public Settings getValueDetails(String name, String id) {
        Settings iteam = repository.getValueDetails(name,id);
        return iteam;
    }
    public Settings getValueDetails(String name) {
        List<Settings> iteam = repository.getAllIteams();
        for (Settings a:iteam
        ) {if(a.getName().equals(name))
            return a;
        }
        return null;    }
    public List<Settings> getAllIteams(){
        return repository.getAllIteams();
    }


}