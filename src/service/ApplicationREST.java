/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import appLogic.AppLogicFacade;
import entity.Application;
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
@Path("application")
public class ApplicationREST {
    @Context
    private UriInfo context;

    public ApplicationREST() {
    }
    
    /**
     * Retrieves representation of an instance of ServiceREST
     *
     * @param userTypeId
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Application> findAll(@QueryParam("userTypeId") Integer userTypeId) {
        AppLogicFacade obj = new AppLogicFacade();
        return obj.selectApplication(userTypeId);
    }
    
    /**
     * PUT method for updating or creating an instance of ServiceREST
     *
     * @param content representation for the resource
     * @param token
     * @param email
     * @param locationString
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String putJson(String content, @QueryParam("token") String token, @QueryParam("email") String email, @QueryParam("location") String locationString) {
        String message = "not implemented yet";
        return message;
    }
}
