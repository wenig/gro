package org.battlehack.gro.rest;

import org.battlehack.gro.domain.entities.Item;
import org.battlehack.gro.domain.tos.FilterTO;
import org.battlehack.gro.services.ItemsService;
import org.battlehack.gro.tos.ItemTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by aakhmerov on 21/06/14.
 */
@Path("/items")
@Component
public class ItemsRestService {

    @Autowired
    private ItemsService itemsService;


    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/createItem")
    public Item createItem(ItemTO toCreate) {
        Item result = itemsService.createItem(toCreate);
        return result;
    }

    /**
     * Find items based on common filtering criterion
     *
     * @param filter
     * @return
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/find")
    public Collection<Item> findFiltered(FilterTO filter) {
        return itemsService.findItems(filter);
    }
}
