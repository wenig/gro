package org.battlehack.gro.services;

import org.battlehack.gro.domain.entities.Item;
import org.battlehack.gro.domain.repositories.ItemRepository;
import org.battlehack.gro.domain.repositories.UserDataRepository;
import org.battlehack.gro.domain.tos.FilterTO;
import org.battlehack.gro.tos.ItemTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by aakhmerov on 21/06/14.
 */
@Component
public class ItemsService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    public Item createItem(ItemTO toCreate) {

        return itemRepository.save(this.composeEntity(toCreate));
    }

    private Item composeEntity(ItemTO toCreate) {
        Item result = new Item();
        if (toCreate.getUserId() != null) {
            result.setUserData(userDataRepository.findOne(toCreate.getUserId()));
        }
        result.setImage(toCreate.getImage());
        result.setName(toCreate.getName());
        result.setQuantity(toCreate.getQuantity());
        result.setTags(toCreate.getTags());
        return result;
    }


    /**
     * Search with common filtering criterion.
     *
     * TODO: refactor using custom repository implementation
     * @param filter
     * @return
     */
    public Collection<Item> findItems(FilterTO filter) {
        return itemRepository.findFiltered(filter);
    }
}
