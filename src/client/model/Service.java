/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import static client.model.Client.fromJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import util.Utility;
import util.Utility.JsonDateDeserializer;

/**
 *
 * @author Juan
 */
public class Service {

    private Integer id;
    private Integer clientId;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private Date cancelDate;
    private Double cost;
    private Double latitudePickup;
    private Double longitudePickup;
    private Double latitudeDestination;
    private Double longitudeDestination;
    private String streetAddressPickup;
    private String cityPickup;
    private String statePickup;
    private String zipcodePickup;
    private String streetAddressDestination;
    private String cityDestination;
    private String stateDestination;
    private String zipcodeDestination;
    private String clientDescription;
    private String towerDescription;

    public Service() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getLatitudePickup() {
        return latitudePickup;
    }

    public void setLatitudePickup(Double latitudePickup) {
        this.latitudePickup = latitudePickup;
    }

    public Double getLongitudePickup() {
        return longitudePickup;
    }

    public void setLongitudePickup(Double longitudePickup) {
        this.longitudePickup = longitudePickup;
    }

    public Double getLatitudeDestination() {
        return latitudeDestination;
    }

    public void setLatitudeDestination(Double latitudeDestination) {
        this.latitudeDestination = latitudeDestination;
    }

    public Double getLongitudeDestination() {
        return longitudeDestination;
    }

    public void setLongitudeDestination(Double longitudeDestination) {
        this.longitudeDestination = longitudeDestination;
    }

    public String getStreetAddressPickup() {
        return streetAddressPickup;
    }

    public void setStreetAddressPickup(String streetAddressPickup) {
        this.streetAddressPickup = streetAddressPickup;
    }

    public String getStatePickup() {
        return statePickup;
    }

    public void setStatePickup(String statePickup) {
        this.statePickup = statePickup;
    }

    public String getZipcodePickup() {
        return zipcodePickup;
    }

    public void setZipcodePickup(String zipcodePickup) {
        this.zipcodePickup = zipcodePickup;
    }

    public String getStreetAddressDestination() {
        return streetAddressDestination;
    }

    public void setStreetAddressDestination(String streetAddressDestination) {
        this.streetAddressDestination = streetAddressDestination;
    }

    public String getStateDestination() {
        return stateDestination;
    }

    public void setStateDestination(String stateDestination) {
        this.stateDestination = stateDestination;
    }

    public String getZipcodeDestination() {
        return zipcodeDestination;
    }

    public void setZipcodeDestination(String zipcodeDestination) {
        this.zipcodeDestination = zipcodeDestination;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public String getTowerDescription() {
        return towerDescription;
    }

    public void setTowerDescription(String towerDescription) {
        this.towerDescription = towerDescription;
    }

    public String getCityPickup() {
        return cityPickup;
    }

    public void setCityPickup(String cityPickup) {
        this.cityPickup = cityPickup;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public static String toJson(List<Service> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<Service>>() {
        }.getType());
        return gsonString;
    }

    public static List<Service> fromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        List<Service> list = gson.fromJson(json, new TypeToken<List<Service>>() {
        }.getType());
        return list;
    }

    public boolean create(String token, String email, List<Tower> listTower) {
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.SERVICE_CREATE_PATH;
        List<Service> list = new ArrayList<Service>();
        list.add(this);
        String body = toJson(list);
        body += Tower.toJson(listTower);
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", "" + clientId);
        parameters.put("token", token);
        parameters.put("email", email);
        message = conn.postMethodText(path, parameters, body);
        return message != null && !"".equals(message);
    }

    public List<Service> selectClientService(String email, String token) {
        List<Service> list;
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.SERVICE_CLIENT_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("token", token);
        message = conn.getMethod(path, parameters);
        list = fromJson(message);
        return list;
    }

}
