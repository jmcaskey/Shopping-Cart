package com.jmcaskey.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmcaskey.auth.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUsername ( String username );
}
