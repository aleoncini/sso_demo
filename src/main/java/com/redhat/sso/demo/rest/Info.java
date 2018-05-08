package com.redhat.sso.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path(value="/info")
public class Info {
    private static final Logger logger = Logger.getLogger("com.redhat.sso.demo");

    @GET
    public Response info() {
        return Response.status(200).entity("Generic server info REST Service").build();
    }

}