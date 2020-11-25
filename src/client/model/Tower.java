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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import util.Utility;

/**
 *
 * @author Juan
 */
public class Tower extends User {

    private Integer id;
    private String companyName;
    private String permitNumber;
    private Double latitude;
    private Double longitude;
    private Double priceMile;

    public Tower() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPriceMile() {
        return priceMile;
    }

    public void setPriceMile(Double priceMile) {
        this.priceMile = priceMile;
    }

    public boolean create() {
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.TOWER_CREATE_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", getEmail());
        List<Tower> list = new ArrayList<Tower>();
        list.add(this);
        String content = toJson(list);
        message = conn.postMethod(path, parameters, content);
        return message!=null;
    }
    
    public List<Tower> selectAll(String token){
        List<Tower> list = null;
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.TOWER_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", getEmail());
        parameters.put("token", token);
        try{
            message = conn.getMethod(path, parameters);
            if(message!=null){
                list = fromJson(message);
            }
        }catch(Exception e){
            //TODO: Handle error NaN
        }
        return list;
    }
    
    public Tower selectByEmailTower(String token){
        List<Tower> list = null;
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.TOWER_BY_EMAIL_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", getEmail());
        parameters.put("token", token);
        try{
            message = conn.getMethod(path, parameters);
            if(message!=null){
                list = fromJson(message);
            }
        }catch(Exception e){
            //TODO: Handle error NaN
        }
        return list.get(0);
    }
    
    public static String toJson(List<Tower> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<Tower>>() {
        }.getType());
        return gsonString;
    }
    
    public static List<Tower> fromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<Tower> list = gson.fromJson(json, new TypeToken<List<Tower>>() {
        }.getType());
        return list;
    }

    public Tower selectByEmailTower(String token, String email) {
        Tower tower = null;
        List<Tower> list;
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.TOWER_BY_EMAIL_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("token", token);
        message = conn.getMethod(path, parameters);
        list = fromJson(message);
        if(list !=null && list.size()>0){
            tower = list.get(0);
        }
        return tower;
    }

    boolean update(String token) {
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.TOWER_CREATE_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", getEmail());
        parameters.put("token", token);
        List<Tower> list = new ArrayList<Tower>();
        list.add(this);
        String content = toJson(list);
        message = conn.postMethod(path, parameters, content);
        return message!=null;
    }
    
    
}
