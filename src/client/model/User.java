/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import util.Utility;

/**
 *
 * @author Juan
 */
public class User {

    private String email;
    private String password;
    private Integer userTypeId;
    private String fname;
    private String lname;
    private String phone;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private Date dob;
    private String blocked;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public static String toJsonUser(List<User> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<User>>() {
        }.getType());
        return gsonString;
    }

    public static List<User> fromJsonUser(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<User> list = gson.fromJson(json, new TypeToken<List<User>>() {
        }.getType());
        return list;
    }
    
    //If the user account was blocked return true
    public boolean block(String email){
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.USER_BLOCK_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        message = conn.getMethod(path, parameters);
        if(message!=null){
            return true;
        }else{
            return false;
        }
    }

    //Selects a single User by Email
    public User selectByEmail(String token, String email) {
        User user = null;
        List<User> list;
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.USER_BY_EMAIL_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("token", token);
        message = conn.getMethod(path, parameters);
        list = fromJsonUser(message);
        if(list !=null && list.size()>0){
            user = list.get(0);
        }
        return user;
    }

    //Creates the HTTP conection to the API Server
    //returns 'true' if email and password matches in Server
    public String login(String email, String password) {
        String key;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.LOGIN_PATH;

        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("password", password);

        key = conn.getMethod(path, parameters);

        return key;
    }

    public boolean logout(String token, String email) {
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.LOGOUT_PATH;

        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("token", token);

        return conn.getMethod(path, parameters).contains("true");
    }

    public boolean findByEmail() {
        User user = null;
        List<User> list;
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.USER_EMAIL_EXISTS_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        message = conn.getMethod(path, parameters);
        if(message!=null){
            return message.contains("true");
        }else{
            return false;
        }
    }

}
