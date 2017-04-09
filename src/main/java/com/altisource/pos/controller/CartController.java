package com.altisource.pos.controller;

import com.altisource.pos.domain.Cart;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/carts", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(OK)
    public Cart getCart(@PathVariable long id) throws PosApplicationException {
        return cartService.getCart(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createCart(@RequestBody Cart cart) throws PosApplicationException {
        cartService.createCart(cart);
    }

    @RequestMapping(value = "/{cartId}/addItem/{productId}", method = PUT)
    @ResponseStatus(OK)
    public void addItemToCart(@PathVariable long cartId, @PathVariable long productId) throws PosApplicationException {
        cartService.addItemToCart(cartId, productId);
    }

    @RequestMapping(value = "/{cartId}/removeItem/{productId}", method = PUT)
    @ResponseStatus(OK)
    public void removeItemFromCart(@PathVariable long cartId, @PathVariable long productId) throws PosApplicationException {
        cartService.removeItemFromCart(cartId, productId);
    }

    @RequestMapping(value = "/{cartId}/updateCount/{productId}", method = PUT)
    @ResponseStatus(OK)
    public void removeItemFromCart(@PathVariable long cartId, @PathVariable long productId, @RequestParam final long itemCount) throws PosApplicationException {
        cartService.updateItemCountInCart(cartId, productId, itemCount);
    }
}
