package org.battlehack.gro.domain.repositories;

import org.battlehack.gro.domain.entities.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aakhmerov on 21/06/14.
 */
public interface UserDataRepository extends CrudRepository<UserData, Long> {

    @Query("SELECT distinct a FROM UserData a WHERE a.email=?1")
    UserData findOneByUsername(String email);
}
