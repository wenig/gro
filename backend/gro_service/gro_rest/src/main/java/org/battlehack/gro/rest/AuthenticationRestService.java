package org.battlehack.gro.rest;

import org.battlehack.gro.domain.entities.UserData;
import org.battlehack.gro.services.AuthenticationService;
import org.battlehack.gro.tos.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by aakhmerov on 21/06/14.
 */
@Path("/authentication")
@Component
public class AuthenticationRestService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationRestService.class);

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * attempt to authenticate user based on his login and password information,
     * if successful full user entity is returned, null pointer returned otherwise
     *
     * @param userData - user data TO containing password and email
     * @return if successful full user entity is returned, null pointer returned otherwise
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/login")
    public UserData login(UserData userData) {
        userData = authenticationService.authenticate(userData);
        logger.debug("authenticating user " + userData);
//      TODO: get rid of dirty hack using faster xml JSON ids
//        userData.getEconomicalData().setUserData(null);
        return userData;
    }
}
