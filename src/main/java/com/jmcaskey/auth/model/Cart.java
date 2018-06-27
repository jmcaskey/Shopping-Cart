package com.jmcaskey.auth.model;

import java.util.HashSet;
import java.util.Set;
import com.jmcaskey.auth.model.Item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table ( name = "cart" )
public class Cart {
    private Long id;
    private String username;
    public Set<Item> items = new HashSet<Item>();
    public Order order;

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername ( String username ) {
        this.username = username;
    }

    @OneToMany(
            cascade = CascadeType.ALL, 
            orphanRemoval = false
        )
    public Set<Item> getItems () {
        return items;
    }

    public void setItems ( Set<Item> items ) {
        this.items = items;
    }
    
    @OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
