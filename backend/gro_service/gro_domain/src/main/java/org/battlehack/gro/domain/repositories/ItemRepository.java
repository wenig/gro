package org.battlehack.gro.domain.repositories;

import org.battlehack.gro.domain.entities.Item;
import org.battlehack.gro.domain.entities.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aakhmerov on 21/06/14.
 */
public interface ItemRepository extends CrudRepository<Item, Long> ,ItemRepositoryCustom{
    @Query("SELECT distinct a FROM Item a WHERE a=?1")
    Item findWithUser(Item destinationItem);
}
