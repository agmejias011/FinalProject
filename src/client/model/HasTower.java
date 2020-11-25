/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.util.Date;
import java.util.HashMap;
import util.Utility;

/**
 *
 * @author Juan
 */
public class HasTower {

    private Integer serviceId;
    private Integer towerId;
    private Date clientApprovedDate;
    private Date towerAcceptDate;
    private Date towerDeclineDate;
    private Integer clientRating;
    private String clientComment;
    private Integer towerRating;
    private String towerComment;

    public HasTower() {
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getTowerId() {
        return towerId;
    }

    public void setTowerId(Integer towerId) {
        this.towerId = towerId;
    }

    public Integer getClientRating() {
        return clientRating;
    }

    public void setClientRating(Integer clientRating) {
        this.clientRating = clientRating;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public Integer getTowerRating() {
        return towerRating;
    }

    public void setTowerRating(Integer towerRating) {
        this.towerRating = towerRating;
    }

    public String getTowerComment() {
        return towerComment;
    }

    public void setTowerComment(String towerComment) {
        this.towerComment = towerComment;
    }

    public Date getClientApprovedDate() {
        return clientApprovedDate;
    }

    public void setClientApprovedDate(Date clientApprovedDate) {
        this.clientApprovedDate = clientApprovedDate;
    }

    public Date getTowerAcceptDate() {
        return towerAcceptDate;
    }

    public void setTowerAcceptDate(Date towerAcceptDate) {
        this.towerAcceptDate = towerAcceptDate;
    }

    public Date getTowerDeclineDate() {
        return towerDeclineDate;
    }

    public void setTowerDeclineDate(Date towerDeclineDate) {
        this.towerDeclineDate = towerDeclineDate;
    }
    
    public boolean acceptRequest(String token, String email) {
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.ACCEPT_REQUEST_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("token", token);
        parameters.put("serviceId", getServiceId().toString());
        parameters.put("towerId", getTowerId().toString());
        message = conn.getMethod(path, parameters);
        return message.equals("true");
    }
    
    public boolean cancelRequest(String token, String email) {
        String message;
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.DECLINE_REQUEST_PATH;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("email", email);
        parameters.put("token", token);
        parameters.put("serviceId", getServiceId().toString());
        parameters.put("towerId", getTowerId().toString());
        message = conn.getMethod(path, parameters);
        return message.equals("true");
    }

    
}
