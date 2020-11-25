/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import appLogic.AppLogicFacade;
import entity.Tower;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Juan
 */
@Path("tower")
public class TowerREST {
    
    
    @Context
    private UriInfo context;

    public TowerREST() {
    }
    
    /**
     * Retrieves representation of an instance of service.BancoREST
     * @param token
     * @param email
     * @param address
     * @param order
     * @return an instance of java.lang.String
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tower> findAll(@QueryParam("token") String token, @QueryParam("email") String email, @QueryParam("address") String address, @QueryParam("order") Integer order) {
        AppLogicFacade obj = new AppLogicFacade();
        return obj.getTowerList(token, email, address, order);
    }
    
    /**
     * Retrieves representation of an instance of service.BancoREST
     * @param token
     * @param email
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tower> findById(@QueryParam("token") String token, @QueryParam("email") String email, @QueryParam("id") Integer id) {
        AppLogicFacade obj = new AppLogicFacade();
        return obj.getTowerById(token, email, id);
    }

    /**
     * PUT method for updating or creating an instance of BancoREST
     * @param content representation for the resource
     * @param token
     * @param email
     * @param id
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putJson(String content, @QueryParam("token") String token, @QueryParam("email") String email, @QueryParam("id") Integer id) {
        String message = "false";
        AppLogicFacade obj = new AppLogicFacade();
        if(obj.updateTower(content, email, token, id)){
            message = "true";
        }
        return message;
    }
    
    /**
     * POST method for updating or creating an instance of BancoREST
     * @param content representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(String content) {
        //String message;
        AppLogicFacade obj = new AppLogicFacade();
        if(obj.registerTower(content)){
            return "true";
        }
        return "false";
    }
    
    
    
}
