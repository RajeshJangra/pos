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
    public Cart createCart(@RequestBody Cart cart) throws PosApplicationException {
        return cartService.createCart(cart);
    }

    @RequestMapping(value = "/{cartId}/addProduct/{productId}", method = PUT)
    @ResponseStatus(OK)
    public Cart addProductToCart(@PathVariable long cartId, @PathVariable long productId) throws PosApplicationException {
        return cartService.addProductToCart(cartId, productId);
    }

    @RequestMapping(value = "/{cartId}/removeProduct/{productId}", method = PUT)
    @ResponseStatus(OK)
    public Cart removeProductFromCart(@PathVariable long cartId, @PathVariable long productId) throws PosApplicationException {
        return cartService.removeProductFromCart(cartId, productId);
    }

    @RequestMapping(value = "/{cartId}/updateCount/{productId}", method = PUT)
    @ResponseStatus(OK)
    public Cart updateProductCountInCart(@PathVariable long cartId, @PathVariable long productId, @RequestParam final long itemCount) throws PosApplicationException {
        return cartService.updateProductCountInCart(cartId, productId, itemCount);
    }
}
