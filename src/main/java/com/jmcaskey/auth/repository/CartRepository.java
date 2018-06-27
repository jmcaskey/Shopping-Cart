package com.jmcaskey.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmcaskey.auth.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUsername ( String username );
}
