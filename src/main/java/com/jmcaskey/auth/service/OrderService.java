package com.jmcaskey.auth.service;

import java.util.List;

import com.jmcaskey.auth.model.Cart;
import com.jmcaskey.auth.model.Order;
import com.jmcaskey.auth.web.model.Payload;

public interface OrderService {
	
	public List<Order> getAllOrders ();

    public Order getOrderByUsername ( String username );
    
    public String createOrder (Payload payload, Cart cart);

    public void save ( Order order );
}
