package org.battlehack.gro.services;

import org.battlehack.gro.domain.entities.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aakhmerov on 21/06/14.
 */
public abstract class PasswordHashingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordHashingService.class);
    private static final java.lang.String HASH_ALGORYTHM = "SHA-256";

    /**
     * Hash password. return HEX representation
     * TODO: replace with standard spring security mechanics
     *
     * @param password - string representation of password
     * @return
     */
    protected String hashPassword(String password) {
        String result = null;
        if (password != null) {
            MessageDigest sha = null;
            try {
                sha = MessageDigest.getInstance(HASH_ALGORYTHM);
                sha.update(password.getBytes());
                byte[] digest = sha.digest();
                result = new String(Hex.encode(digest));
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error("problem with password hashing",e);
            }

        }
        return result;
    }
}
