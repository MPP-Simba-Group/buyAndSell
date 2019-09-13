package com.mpp.buyAndSell.core.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ADDRESS_ID")
    private long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "address")
    private String address;

    //-----------------------------setters and getters--------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
