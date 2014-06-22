package org.battlehack.gro.services;

import org.battlehack.gro.domain.entities.UserData;
import org.battlehack.gro.domain.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by aakhmerov on 21/06/14.
 */
@Component
public class RegistrationService extends PasswordHashingService{
    @Autowired
    private UserDataRepository userDataRepository;

    /**
     *
     * @param userData
     * @return
     */
    public UserData save(UserData userData) {
        userData.setPassword(this.hashPassword(userData.getPassword()));
        return userDataRepository.save(userData);
    }


}
