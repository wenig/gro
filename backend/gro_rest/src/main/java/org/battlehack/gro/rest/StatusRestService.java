package org.battlehack.gro.rest;

import org.battlehack.gro.tos.Status;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: aakhmerov
 * Date: 5/13/13
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/status")
@Component
public class StatusRestService {


    /**
     * Check that all internals work fine together, through the status service
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/ping")
    public Status getStatus() {
        Status state = new Status();
        state.setStatus("200 OK");
        return state;
    }

}
