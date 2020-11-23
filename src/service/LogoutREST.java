/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import appLogic.AppLogicFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Juan
 */
@Path("logout")
public class LogoutREST {

    //private static final long serialVersionUID = 1L;
    
    @Context
    private UriInfo context;

    public LogoutREST() {
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(@Context HttpHeaders httpHeaders, @QueryParam("token") String authToken, @QueryParam("email") String email) {
        AppLogicFacade appLogic = new AppLogicFacade();
        if(appLogic.logout(authToken, email)){
            return getNoCacheResponseBuilder(Response.Status.OK).entity("true").build();
        }
        return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).entity("false").build();
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setMaxAge(-1);
        cc.setMustRevalidate(true);

        return Response.status(status).cacheControl(cc);
    }

}
