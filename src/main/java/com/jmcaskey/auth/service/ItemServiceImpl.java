package com.jmcaskey.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmcaskey.auth.model.Item;
import com.jmcaskey.auth.model.Cart;
import com.jmcaskey.auth.repository.ItemRepository;
import com.jmcaskey.auth.repository.OrderRepository;
import com.jmcaskey.auth.repository.CartRepository;
import com.jmcaskey.auth.repository.RoleRepository;
import com.jmcaskey.auth.repository.UserRepository;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public List<Item> getAllItems () {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById ( Long id ) {
        return itemRepository.findById( id );
    }

    @Override
    public Cart getCartByUsername ( String username ) {
    	Cart order;
        try {
        	order = cartRepository.findByUsername( username );
        } catch (Exception e) {
    		order = null;
    	}
        if (order == null) {
        	order = new Cart();
        	order.setUsername(username);
        	cartRepository.save(order);
        }
        return order;
    }

    @Override
    public void addItemToCart ( Cart order, Item item ) {
        order.getItems().add( item );
        cartRepository.save( order );
    }

    @Override
    public void save ( Item item ) {
        itemRepository.save( item );
    }

    @Override
    public void save ( Cart order ) {
        cartRepository.save( order );
    }

}
