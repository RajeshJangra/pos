package com.altisource.pos.service;

import com.altisource.pos.domain.Cart;
import com.altisource.pos.domain.ProductOrder;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.CartRepository;
import com.altisource.pos.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    public Cart createCart(final Cart cart) throws PosApplicationException {
        if (cartRepository.exists(cart.getId())) {
            String message = "Cart can not be created. Cart: " + cart.getId() + " already exists";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(final long cartId, final long productId) throws PosApplicationException {
        Cart cart = getCart(cartId);
        Optional<ProductOrder> orderProductOptional = cart.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();

        if (orderProductOptional.isPresent()) {
            String message = "Product can not be added. Product: " + productId + " already exists in the cart: " + cartId;
            LOGGER.error(message);
            throw new PosApplicationException(message);
        } else {
            cart.getProductOrders().add(new ProductOrder(productRepository.findOne(productId), 1));
        }
        return cartRepository.save(cart);
    }

    public Cart getCart(final long cartId) throws PosApplicationException {
        Cart cart = cartRepository.findOne(cartId);
        if (cart == null) {
            String message = "Cart: " + cartId + " does not exist";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        return cart;
    }

    public Cart updateProductCountInCart(final long cartId, final long productId, final long itemCount) throws PosApplicationException {
        Cart cart = getCart(cartId);
        Optional<ProductOrder> orderProductOptional = cart.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();

        if (orderProductOptional.isPresent()) {
            orderProductOptional.get().setCount(itemCount);
        } else {
            String message = "Product count can not be updated. Product: " + productId + " does not exist in the cart: " + cartId;
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(final long cartId, final long productId) throws PosApplicationException {
        Cart cart = getCart(cartId);
        Optional<ProductOrder> optionalOrderProduct = cart.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();

        if (optionalOrderProduct.isPresent()) {
            cart.getProductOrders().remove(optionalOrderProduct.get());
            return cartRepository.save(cart);
        } else {
            String message = "Product can not be removed. Product: " + productId + " does not exist in the cart: " + cartId;
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
    }
}
