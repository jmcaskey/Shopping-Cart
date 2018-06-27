package com.jmcaskey.auth.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jmcaskey.auth.model.Item;
import com.jmcaskey.auth.model.Cart;
import com.jmcaskey.auth.service.ItemService;
import com.jmcaskey.auth.service.OrderService;
import com.jmcaskey.auth.service.SecurityService;
import com.jmcaskey.auth.service.UserService;
import com.jmcaskey.auth.validator.UserValidator;
import com.jmcaskey.auth.web.model.Payload;

@Controller
public class ItemController {
    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping ( value = "/item/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public List<Item> getAllItems ( Model model ) {
        return itemService.getAllItems();
    }

    @RequestMapping ( value = "/item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Item getItem ( Model model, long id ) {
        return itemService.getItemById( id );
    }

    @RequestMapping ( value = "/item/edit", method = RequestMethod.POST )
    public String editItem ( Model model, long id, @RequestBody Item newItem ) {
        Item item = itemService.getItemById( id );
        item.setName( newItem.getName() );
        item.setDescription( newItem.getDescription() );
        item.setPrice( newItem.getPrice() );
        item.setImage( newItem.getImage() );
        itemService.save( item );
        return "admin/home.jsp";
    }

    @RequestMapping ( value = "/order/add", method = RequestMethod.POST )
    @ResponseBody
    public String addItemToOrder ( Model model, Principal principal, @RequestBody Item item ) {
        String username = principal.getName();
        itemService.getItemById(item.getId()).quantity = item.quantity;
        itemService.save(itemService.getItemById(item.getId()));
        for (int i = 0; i < item.quantity; i++) {
        	itemService.addItemToCart( itemService.getCartByUsername( username ), item );
        }
        return "/customer/home.jsp?success";
    }

    @RequestMapping ( value = "/order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Cart getOrder ( Model model, Principal principal ) {
        return itemService.getCartByUsername( principal.getName() );
    }

    @RequestMapping ( value = "/order/confirm", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public String confirmOrder ( Model model, Principal principal, @RequestBody Payload payload ) {
        Cart cart = itemService.getCartByUsername(principal.getName());
        if (cart.items.size() == 0) {
        	return "error";
        }
        
    	return orderService.createOrder(payload, cart);
    }

}
