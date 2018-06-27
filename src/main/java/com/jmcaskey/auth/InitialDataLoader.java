package com.jmcaskey.auth;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jmcaskey.auth.model.Item;
import com.jmcaskey.auth.model.Role;
import com.jmcaskey.auth.model.User;
import com.jmcaskey.auth.repository.ItemRepository;
import com.jmcaskey.auth.repository.RoleRepository;
import com.jmcaskey.auth.repository.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @SuppressWarnings ( "unchecked" )
    @Override
    @Transactional
    public void onApplicationEvent ( final ApplicationReadyEvent event ) {

        if ( alreadySetup ) {
            return;
        }
        Role adminRole = new Role();
        adminRole.setName( "ROLE_ADMIN" );
        roleRepository.save( adminRole );

        final Role userRole = new Role();
        userRole.setName( "ROLE_USER" );
        userRole.setUsers( new HashSet<User>() );
        roleRepository.save( userRole );

        adminRole = roleRepository.findByName( "ROLE_ADMIN" );
        final User user = new User();
        user.setUsername( "admin" );
        user.setPassword( bCryptPasswordEncoder.encode( "admin" ) );
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add( adminRole );
        user.setRoles( roles );
        userRepository.save( user );

        Item item1 = new Item();

        item1.setName( "Item 1" );
        item1.setDescription( "This is a sample item description for " + item1.getName() );
        item1.setPrice( 1.99 );
        item1.setImage( "https://png.icons8.com/color/48/000000/1-c.png" );
        itemRepository.save( item1 );

        Item item2 = new Item();

        item2.setName( "Item 2" );
        item2.setDescription( "This is a sample item description for " + item2.getName() );
        item2.setPrice( 2.99 );
        item2.setImage( "https://png.icons8.com/color/48/000000/2-c.png" );
        itemRepository.save( item2 );

        Item item3 = new Item();

        item3.setName( "Item 3" );
        item3.setDescription( "This is a sample item description for " + item3.getName() );
        item3.setPrice( 3.99 );
        item3.setImage( "https://png.icons8.com/color/48/000000/3-c.png" );
        itemRepository.save( item3 );

        alreadySetup = true;
    }

}
