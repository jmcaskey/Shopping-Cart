package com.jmcaskey.auth.service;

import java.util.List;

import com.jmcaskey.auth.model.Item;
import com.jmcaskey.auth.model.Cart;

public interface ItemService {

    public List<Item> getAllItems ();

    public Item getItemById ( Long id );

    public Cart getCartByUsername ( String username );

    public void addItemToCart ( Cart order, Item item );

    public void save ( Item item );

    public void save ( Cart order );
}
