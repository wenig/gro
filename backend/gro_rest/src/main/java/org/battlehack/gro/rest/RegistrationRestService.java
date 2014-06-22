package org.battlehack.gro.rest;


import org.battlehack.gro.domain.entities.UserData;
import org.battlehack.gro.services.RegistrationService;
import org.battlehack.gro.tos.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by aakhmerov on 21/06/14.
 */
@Path("/registration")
@Component
public class RegistrationRestService {


    @Autowired
    private RegistrationService registrationService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationRestService.class);

    /**
     * Check that all internals work fine together, through the status service
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public UserData register(UserData userData) {
        userData = registrationService.save(userData);
        logger.info("registering user [id :" + userData.getId().intValue() + "]");
        userData.getEconomicalData().setUserData(null);
        return userData;
    }
}
