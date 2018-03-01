package com.aeromexico.pab.web.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Alfredo Estrada
 */
@Path("service1")
public class Service1 {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Service1
     */
    public Service1() {
    }

    /**
     * Retrieves representation of an instance of com.aeromexico.pab.web.rest.Service1
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        return "["+sdf.format(new Date())+"]";
    }

}
