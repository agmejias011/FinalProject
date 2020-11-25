/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.util.HashMap;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import util.Utility;

/**
 *
 * @author Juan
 */
public class RESTConnection {
    private static final String BASE_URL = Utility.BASE_URL;
    private static RESTConnection instance = new RESTConnection();

    public RESTConnection() {
    }

    public static RESTConnection getInstance() {
        return instance;
    }

    public String getMethod(String path, HashMap<String, String> parameters) {
        Response response = null;
        String message = null;
        Client obj = ClientBuilder.newClient();
        WebTarget target = obj.target(BASE_URL + path);

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            target = target.queryParam(key, value);
        }

        try {
            response = target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.getStatus() == 200) {
                message = response.readEntity(String.class);
            }
            
            /*System.out.println("Response status: " + response.getStatus());
            System.out.println("Response reason: " + response.getStatusInfo());
            System.out.println(target.toString());*/
        }

        return message;
    }
    
    public String postMethod(String path, HashMap<String, String> parameters, String body) {
        Response response = null;
        String message = null;
        Client obj = ClientBuilder.newClient();
        WebTarget target = obj.target(BASE_URL + path);

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            target = target.queryParam(key, value);
        }

        try {
            response = target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).post(Entity.entity(body,MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.getStatus() == 200) {
                message = response.readEntity(String.class);
            }
        }

        return message;
    }
    
    public String postMethodText(String path, HashMap<String, String> parameters, String body) {
        Response response = null;
        String message = null;
        Client obj = ClientBuilder.newClient();
        WebTarget target = obj.target(BASE_URL + path);

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            target = target.queryParam(key, value);
        }

        try {
            response = target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).post(Entity.entity(body,MediaType.TEXT_PLAIN));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.getStatus() == 200) {
                message = response.readEntity(String.class);
            }
        }

        return message;
    }
    
    //Handles all PUT Methods
    public String putMethod(String path, HashMap<String, String> parameters, Integer id, String body) {
        Response response = null;
        String message = null;
        Client obj = ClientBuilder.newClient();
        WebTarget target = obj.target(BASE_URL + path);

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            target = target.queryParam(key, value);
        }

        try {
            response = target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).put(Entity.entity(body,MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.getStatus() == 200) {
                message = response.readEntity(String.class);
            }
        }

        return message;
    }
    
    public String deleteMethod(String path, HashMap<String, String> parameters, String id) {
        Response response = null;
        String message = null;
        Client obj = ClientBuilder.newClient();
        WebTarget target = obj.target(BASE_URL + path + "/" + id);

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            target = target.queryParam(key, value);
        }

        try {
            response = target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.getStatus() == 200) {
                message = response.readEntity(String.class);
            }
        }

        return message;
    }

}
