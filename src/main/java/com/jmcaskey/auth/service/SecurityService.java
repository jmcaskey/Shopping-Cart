package com.jmcaskey.auth.service;

public interface SecurityService {
    String findLoggedInUsername();
    
    boolean isAdmin();

    void autologin(String username, String password);
}
