/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import appLogic.AppLogicFacade;
import entity.User;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("user")
public class UserREST {

    @Context
    private UriInfo context;

    public UserREST() {
    }
    
    /**
     * Retrieves representation of an instance of UserREST
     *
     * @param email
     * @param token
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll(@QueryParam("email") String email, @QueryParam("token") String token) {
        List<User> list = null;
        /*AppLogicFacade obj = new AppLogicFacade();
        list = obj.selectAllUser(email, token);*/
        return list;
    }
    
    /**
     * Retrieves representation of an instance of UserREST
     *
     * @param email
     * @param token
     * @return an instance of java.lang.String
     */
    @Path("email")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUserByEmail(@QueryParam("email") String email, @QueryParam("token") String token) {
        List<User> list;
        AppLogicFacade obj = new AppLogicFacade();
        list = obj.selectUserByEmail(email, token);
        return list;
    }
    
    /**
     * Retrieves representation of an instance of UserREST
     *
     * @param email
     * @return an instance of java.lang.String
     */
    @Path("exists")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String findByEmail(@QueryParam("email") String email) {
        String message = "false";
        AppLogicFacade obj = new AppLogicFacade();
        if(obj.findUserByEmail(email)){
            message = "true";
        }
        return message;
    }

    /**
     * PUT method for updating or creating an instance of UserREST
     *
     * @param email
     */
    @Path("block")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void block(@QueryParam("email") String email) {
        AppLogicFacade obj = new AppLogicFacade();
        obj.blockUser(email);
    }

}
