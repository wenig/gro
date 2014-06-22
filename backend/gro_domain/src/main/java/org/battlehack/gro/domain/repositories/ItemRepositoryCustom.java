package org.battlehack.gro.domain.repositories;

import org.battlehack.gro.domain.entities.Item;
import org.battlehack.gro.domain.tos.FilterTO;

import java.util.Collection;

/**
 * Created by aakhmerov on 22/06/14.
 */
public interface ItemRepositoryCustom {
    Collection<Item> findFiltered(FilterTO filter);
}
