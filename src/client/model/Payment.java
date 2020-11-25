/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.util.Date;

/**
 *
 * @author Juan
 */
public class Payment {

    private Integer id;
    private Integer serviceId;
    private Date date;
    private Double amount;
    private String creditCardLast;
    private String paypalAutho;

    public Payment() {
    }

    public Integer getIdpayment() {
        return id;
    }

    public void setIdpayment(Integer idpayment) {
        this.id = idpayment;
    }

    public Integer getIdservice() {
        return serviceId;
    }

    public void setIdservice(Integer idservice) {
        this.serviceId = idservice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreditCardLast() {
        return creditCardLast;
    }

    public void setCreditCardLast(String creditCardLast) {
        this.creditCardLast = creditCardLast;
    }

    public String getPaypalAuto() {
        return paypalAutho;
    }

    public void setPaypalAuto(String paypalAuto) {
        this.paypalAutho = paypalAuto;
    }

    

}
