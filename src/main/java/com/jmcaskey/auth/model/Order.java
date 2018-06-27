package com.jmcaskey.auth.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table ( name = "cOrder" )
public class Order {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String address;
    
    public Cart cart;

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}


}
