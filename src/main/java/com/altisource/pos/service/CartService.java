package com.altisource.pos.service;

import com.altisource.pos.domain.Cart;
import com.altisource.pos.exception.CartValidationException;
import com.altisource.pos.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public List<Cart> getItems() {
        return cartRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createCart(final Cart cart) throws CartValidationException {
        if (cartRepository.exists(cart.getId())) {
            throw new CartValidationException("Item already exists");
        }
        cartRepository.save(cart);
    }

    public void updateCart(final Cart cart) throws CartValidationException {
        if (cartRepository.exists(cart.getId())) {
            cartRepository.save(cart);
        }
        throw new CartValidationException("Item does not exist");
    }

    public void deleteCart(final long id) throws CartValidationException {
        if (cartRepository.exists(id)) {
            cartRepository.delete(id);
        }
        throw new CartValidationException("Item does not exist");
    }

    public Cart getCart(final long id) {
        return cartRepository.findOne(id);
    }
}
