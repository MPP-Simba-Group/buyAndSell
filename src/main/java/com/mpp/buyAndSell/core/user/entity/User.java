package com.mpp.buyAndSell.core.user.entity;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "ORG_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "First_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "WHATSAPP")
    private String whatsapp;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "BLOCKED")
    private Boolean blocked;

    @Column(name="password")
    private String password;
    Boolean Admin;
    Boolean seller;
    Boolean buyer;

    public Boolean getAdmin() {
		return Admin;
	}


	public void setAdmin(Boolean admin) {
		Admin = admin;
	}


	public boolean isSeller() {
		return seller;
	}


	public void setSeller(boolean seller) {
		this.seller = seller;
	}


	public boolean isBuyer() {
		return buyer;
	}


	public void setBuyer(boolean buyer) {
		this.buyer = buyer;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name="token")
    private String token;

    public User() {
		// TODO Auto-generated constructor stub
	}


    public User(long id, String firstName, String lastName, @Email String email,String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password=password;

	}


    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    //--------------------------------setters and getters-------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }

    public Boolean getBuyer() {
        return buyer;
    }

    public void setBuyer(Boolean buyer) {
        this.buyer = buyer;
    }
}
