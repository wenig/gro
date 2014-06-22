package org.battlehack.gro.domain.tos;

import org.battlehack.gro.domain.entities.UserData;

/**
 * Created by aakhmerov on 22/06/14.
 *
 * TO wrapper around filter specification
 */
public class FilterTO {
    private String tags;
    private String name;
    private UserData user;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
