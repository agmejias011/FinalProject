/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import util.Utility;

/**
 *
 * @author Juan
 */
public class Application {
    
    private Integer id;
    private String name;
    private String url;
    
    public Application(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Application> getApplicationByUserTypeId(Integer userTypeId) {
        List<Application> listApp;
        HashMap<Integer, Boolean> list = null;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.APPLICATION_PATH;
        
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("userTypeId", userTypeId.toString());
        String json = conn.getMethod(path, parameters);
        
        return fromJson(json);
    }
    
    
    public static String toJson(List<Application> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<Application>>() {
        }.getType());
        return gsonString;
    }

    public static List<Application> fromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<Application> list = gson.fromJson(json, new TypeToken<List<Application>>() {
        }.getType());
        return list;
    }
    
    
}
