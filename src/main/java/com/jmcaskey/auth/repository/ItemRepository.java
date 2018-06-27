package com.jmcaskey.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmcaskey.auth.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName ( String name );

    Item findById ( Long id );
}
