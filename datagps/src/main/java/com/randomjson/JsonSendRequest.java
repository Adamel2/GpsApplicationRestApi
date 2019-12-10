package com.randomjson;
import com.google.gson.Gson;
import com.gpsdata.LocationRequest;
import com.gpsdata.User;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @apiNote
 *
 * au : eldda adham
 *
 */
import java.io.IOException;
import java.util.*;


public class JsonSendRequest {

    private Date date = new Date();

    /**
     * @// TODO: 11/27/19
     * important ( you need ->-> update the date )
     *
     */
    public  void jsonSend() throws IOException, InterruptedException {



        User firstUser = new User("cohen","tomer","1234rrtgv",true,date.getTime());
        User secondUser = new User("moshe","2222alon","000011",true,date.getTime());

        List<User> list = new ArrayList<>();
        list.add(firstUser);
        String postUrl       = "http://localhost:8080/setData/addUser";
        Gson gson = new Gson();
        HttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString1 = new StringEntity(gson.toJson(list.get(0)));
        StringEntity postingString2 = new StringEntity(gson.toJson(secondUser));


        post.setEntity(postingString1);
        post.setHeader("Content-type", "application/json");
        httpClient.execute(post);

        Thread.sleep(1000);

        post.setEntity(postingString2);
        post.setHeader("Content-type", "application/json");
        httpClient.execute(post);

        sendData();



    }

    public void sendData() throws IOException, InterruptedException {
        String postUrl       = "http://localhost:8080/setData/addLocationRequest";
        Gson gson = new Gson();
        HttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        byte[] array = new byte[2];
        new Random().nextBytes(array);

        Random rand = new Random();
        for (int i = 0; i <10 ; i++) {
            post.setEntity(new StringEntity(gson.toJson(new LocationRequest("123456789a", 31.121f+i,32.111f+rand.nextInt(2),date.getTime()))));
            post.setHeader("Content-type", "application/json");
            httpClient.execute(post);
            Thread.sleep(5000);
            post.setEntity(new StringEntity(gson.toJson(new LocationRequest("123456789m", 34.121f+i,35.111f+rand.nextInt(2),date.getTime()))));
            post.setHeader("Content-type", "application/json");
            httpClient.execute(post);
            Thread.sleep(5000);

        }



    }


}
