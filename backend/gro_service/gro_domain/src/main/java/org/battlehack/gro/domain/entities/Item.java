package org.battlehack.gro.domain.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 21/06/14.
 */
@XmlRootElement
@Entity
@Table
public class Item extends AbstractPersistable<Long> {
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] image;
    private String name;
    private String tags;
    private int quantity;

    @ManyToOne (fetch = FetchType.LAZY)
    private UserData userData;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }


}
