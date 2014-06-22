package org.battlehack.gro.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

/**
 * Created by aakhmerov on 21/06/14.
 */
@XmlRootElement
@Entity
@Table(name = "GRO_USER")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@userId")
public class UserData {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;


    private String firstName;
    private String lastName;
    private String address;
    @NotNull
    @Column (name = "email", unique = true)
    private String email;
    private String phone;
    @NotNull
    private String password;
    private String birthday;

    @OneToMany (mappedBy = "userData",fetch = FetchType.LAZY)
    private Set<Item> items;

//  TODO: force lazy init here
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userData")
    private EconomicalData economicalData = new EconomicalData();

    public UserData() {
//      TODO: get rid of static initialization
        this.economicalData.setNumber("4111111111111111");
        this.economicalData.setCvv("111");
        this.economicalData.setExpiryMonth("11");
        this.economicalData.setExpiryYear("2015");
        this.economicalData.setUserData(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public EconomicalData getEconomicalData() {
        return economicalData;
    }

    public void setEconomicalData(EconomicalData economicalData) {
        this.economicalData = economicalData;
    }
}
