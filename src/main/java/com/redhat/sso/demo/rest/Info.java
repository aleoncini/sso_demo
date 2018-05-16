package com.redhat.sso.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Path(value="/info")
public class Info {
    private static final Logger logger = Logger.getLogger("com.redhat.sso.demo");

    @GET
    public Response info() {
        return Response.status(200).entity("Generic server info REST Service").build();
    }

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.status(200).entity("ok").build();
    }

    @GET
    @Produces("application/json")
    @Path("/user")
    public Response issueUserInfo(@Context HttpHeaders headers) {
        String info = this.parseHeaders(headers);
        return Response.status(200).entity(info).build();
    }

    private String parseHeaders(HttpHeaders headers) {
        StringBuffer sb = new StringBuffer("{");
        boolean isFirst = true;
        logger.info("========================= list of cookies ========================");
        Map<String, Cookie> cookies = headers.getCookies();
        for (String cookieName: cookies.keySet()) {
            logger.info("========================= " + cookieName);
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(",");
            }
            sb.append("\"").append(cookieName).append("\":\"").append(cookies.get(cookieName).getValue()).append("\"");
        }
        isFirst = true;
        logger.info("========================= list of headers ========================");
        MultivaluedMap<String, String> headerList = headers.getRequestHeaders();
        for (String hName: headerList.keySet()) {
            logger.info("========================= " + hName);
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(",");
            }
            sb.append("\"").append(hName).append("\":\"").append(formatHeaderValue(headerList.get(hName))).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    private String formatHeaderValue(List<String> strings) {
        StringBuffer sb = new StringBuffer();
        boolean isFirst = true;
        for (String s : strings) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(" ");
            }
            sb.append(s);
        }
        return sb.toString();
    }

}