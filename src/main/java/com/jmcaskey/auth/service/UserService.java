package com.jmcaskey.auth.service;

import com.jmcaskey.auth.model.User;

public interface UserService {
    void save(User user);
    
    User findByUsername(String username);
}
