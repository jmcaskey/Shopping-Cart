package com.jmcaskey.auth.service;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmcaskey.auth.model.Item;
import com.jmcaskey.auth.model.Order;
import com.jmcaskey.auth.model.Cart;
import com.jmcaskey.auth.repository.ItemRepository;
import com.jmcaskey.auth.repository.OrderRepository;
import com.jmcaskey.auth.repository.CartRepository;
import com.jmcaskey.auth.repository.RoleRepository;
import com.jmcaskey.auth.repository.UserRepository;
import com.jmcaskey.auth.web.model.Payload;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    
    protected static SecureRandom random = new SecureRandom();
    
    @Override
    public List<Order> getAllOrders () {
        return orderRepository.findAll();
    }


    @Override
    public Order getOrderByUsername ( String username ) {
        return orderRepository.findByUsername( username );
    }
    
    public String createOrder (Payload payload, Cart cart) {
    	
    	Order order = new Order();
    	order.setName(payload.getName());
    	order.setEmail(payload.getEmail());
    	order.setAddress(payload.getAddress());
    	order.setName(cart.getUsername());
    	order.setCart(cart);
    	order.setId(Math.abs( random.nextLong()));
    	save(order);
    	cart.setUsername("__ARCHIVE_" + cart.getUsername() + "__");
    	cartRepository.save(cart);
    	return "" + order.getId();
    }


    @Override
    public void save ( Order order ) {
        orderRepository.save( order );
    }

}
