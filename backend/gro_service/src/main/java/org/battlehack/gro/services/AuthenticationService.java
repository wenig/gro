package org.battlehack.gro.services;

import org.battlehack.gro.domain.entities.UserData;
import org.battlehack.gro.domain.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by aakhmerov on 21/06/14.
 *
 * TODO: replace by spring authentication provider implementation
 */
@Component
public class AuthenticationService extends PasswordHashingService{

    @Autowired
    private UserDataRepository userDataRepository;

    /**
     * Lookup user entity based on username property,
     * compare password hashes if passwords match return full entity
     * if not return null
     *
     * @param userData - TO wrapper with username and password
     * @return
     */
    public UserData authenticate(UserData userData) {
        UserData result = null;
        UserData persisted = userDataRepository.findOneByUsername(userData.getEmail());
        if (userData.getPassword() != null &&
                persisted != null &&
                this.hashPassword(userData.getPassword()).equals(persisted.getPassword())) {
            result = persisted;
        }
        return result;
    }
}
