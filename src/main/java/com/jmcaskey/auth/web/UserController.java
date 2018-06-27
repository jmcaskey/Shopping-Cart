package com.jmcaskey.auth.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jmcaskey.auth.model.User;
import com.jmcaskey.auth.service.SecurityService;
import com.jmcaskey.auth.service.UserService;
import com.jmcaskey.auth.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping ( value = "/registration", method = RequestMethod.GET )
    public String registration ( final Model model ) {
        model.addAttribute( "userForm", new User() );

        return "registration";
    }

    @RequestMapping ( value = "/registration", method = RequestMethod.POST )
    public String registration ( @ModelAttribute ( "userForm" ) final User userForm, final BindingResult bindingResult,
            final Model model ) {
        userValidator.validate( userForm, bindingResult );

        if ( bindingResult.hasErrors() ) {
            return "registration";
        }

        userService.save( userForm );

        securityService.autologin( userForm.getUsername(), userForm.getPasswordConfirm() );

        return "redirect:/welcome";
    }

    @RequestMapping ( value = "/login", method = RequestMethod.GET )
    public String login ( final Model model, final String error, final String logout ) {
        if ( error != null ) {
            model.addAttribute( "error", "Your username and password is invalid." );
        }

        if ( logout != null ) {
            model.addAttribute( "message", "You have been logged out successfully." );
        }

        return "login";
    }

    @RequestMapping ( value = { "/", "/welcome" }, method = RequestMethod.GET )
    public String welcome ( final Model model, final Principal principal ) {
        if ( securityService.isAdmin() ) {
            return "redirect:/admin/home.jsp";
        }
        return "redirect:/customer/home.jsp";
    }
}
