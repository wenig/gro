package org.battlehack.gro.rest;

import org.battlehack.gro.domain.entities.Exchange;
import org.battlehack.gro.domain.entities.UserData;
import org.battlehack.gro.services.ExchangeService;
import org.battlehack.gro.tos.CreateOfferTO;
import org.battlehack.gro.tos.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by aakhmerov on 22/06/14.
 */
@Path("/offers")
@Component
public class OfferRestService {

    @Autowired
    private ExchangeService exchangeService;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/createOffer")
    public Exchange createOffer(CreateOfferTO createOfferTO) {
        Exchange result = exchangeService.createOffer(createOfferTO);
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/getOffers/{id}")
    public Collection<Exchange> createOffer(@PathParam(value = "id") Integer id) {
        return exchangeService.findUserOffers(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/confirm/{id}")
    public Status confirm(@PathParam(value = "id") Integer id) {
        return exchangeService.confirmOffer(id);
    }
}
