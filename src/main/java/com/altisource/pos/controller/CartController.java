package com.altisource.pos.controller;

import com.altisource.pos.domain.Cart;
import com.altisource.pos.exception.CartValidationException;
import com.altisource.pos.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/carts", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Cart getUser(@PathVariable long id) throws CartValidationException {
        return cartService.getCart(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@RequestBody Cart cart) throws CartValidationException {
        cartService.createCart(cart);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCart(@RequestBody Cart cart) throws CartValidationException {
        cartService.updateCart(cart);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCart(@PathVariable long id) throws CartValidationException {
        cartService.deleteCart(id);
    }


    @ExceptionHandler(CartValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(CartValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
