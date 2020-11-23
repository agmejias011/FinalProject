/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import appLogic.AppLogicFacade;
import entity.HasTower;
import util.Location;
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
@Path("hasTower")
public class HasTowerREST {

    @Context
    private UriInfo context;

    public HasTowerREST() {
    }

    /**
     * Retrieves representation of an instance of HasTowerREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HasTower> findAll() {
        HasTower obj = new HasTower();
        return obj.selectAll();
    }

    /**
     * PUT method for updating or creating an instance of HasTowerREST
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @Path("accept")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String accept(String content, @QueryParam("token") String authToken, @QueryParam("email") String email, @QueryParam("serviceId") Integer serviceId) {
        String message = "false";
        Location location = null;
        if (authToken != null && email != null) {
            AppLogicFacade obj = new AppLogicFacade();
            if(obj.acceptService(content, authToken, email, serviceId)){
                message = "true";
            }
        }
        return message;
    }

    @Path("decline")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String decline(String content, @QueryParam("token") String authToken, @QueryParam("email") String email, @QueryParam("serviceId") Integer serviceId) {
        String message = "false";
        Location location = null;
        if (authToken != null && email != null) {
            AppLogicFacade obj = new AppLogicFacade();
            if(obj.declineService(content, authToken, email, serviceId)){
                message = "true";
            }
        }
        return message;
    }

}
