package org.battlehack.gro.domain.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 22/06/14.
 */
@XmlRootElement
@Entity
@Table
public class EconomicalData extends AbstractPersistable<Long> {
    private String number;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;

    @OneToOne (fetch = FetchType.LAZY)
    @MapsId
    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }
}
