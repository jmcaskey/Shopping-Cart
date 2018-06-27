package com.jmcaskey.auth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmcaskey.auth.model.Role;
import com.jmcaskey.auth.model.User;
import com.jmcaskey.auth.repository.RoleRepository;
import com.jmcaskey.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save ( final User user ) {
        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ) );
        final HashSet<Role> roles = new HashSet<Role>();
        user.setRoles( roles );
        userRepository.save( user );
    }

    @Override
    public User findByUsername ( final String username ) {
        return userRepository.findByUsername( username );
    }
}
