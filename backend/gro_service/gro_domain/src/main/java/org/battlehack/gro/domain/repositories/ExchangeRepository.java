package org.battlehack.gro.domain.repositories;

import org.battlehack.gro.domain.entities.Exchange;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by aakhmerov on 22/06/14.
 */
public interface ExchangeRepository extends CrudRepository <Exchange, Long> {

    @Query("SELECT a FROM Exchange a LEFT JOIN FETCH a.destinationItem di LEFT JOIN FETCH di.userData ud WHERE ud.id=?1")
    Collection<Exchange> findForDestinationUser(Long id);

    @Query("SELECT a FROM Exchange a LEFT JOIN FETCH a.sourceItem si LEFT JOIN FETCH si.userData su LEFT JOIN FETCH a.destinationItem di LEFT JOIN FETCH di.userData du WHERE a.id=?1 ")
    Exchange findOneWithItemsAndUsers(Long aLong);

}
