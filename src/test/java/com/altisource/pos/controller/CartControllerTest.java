package com.altisource.pos.controller;

import com.altisource.pos.domain.*;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.ProductRepository;
import com.altisource.pos.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * BillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 10, 2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @InjectMocks
    CartController cartController;
    @Mock
    CartService cartService;


    @Test
    public void testCreateCartSuccessful() throws Exception {
        long cartId = 1l;
        Cart expected = getCart(cartId);
        when(cartService.createCart(expected)).thenReturn(expected);
        Cart returned = cartController.createCart(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateCartAndCartAlreadyExists() throws Exception {
        long cartId = 1l;
        Cart expected = getCart(cartId);
        when(cartService.createCart(expected)).thenThrow(new PosApplicationException("Cart can not be created. Cart: " + cartId + " already exists"));
        cartController.createCart(expected);
    }

    @Test
    public void testAddProductToCartSuccessful() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        Cart expected = getCart(cartId);
        expected.getProductOrders().get(0).getProduct().setId(productId);
        when(cartService.addProductToCart(cartId, productId)).thenReturn(expected);
        Cart returned = cartController.addProductToCart(cartId, productId);
        Optional<ProductOrder> productOrder = returned.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();
        assertEquals(1l, productOrder.get().getProduct().getId());
    }

    @Test(expected = PosApplicationException.class)
    public void testAddProductToCartAndCartDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        when(cartService.addProductToCart(cartId, productId)).thenThrow(new PosApplicationException("Cart: " + cartId + " does not exist"));
        cartController.addProductToCart(cartId, productId);
    }

    @Test(expected = PosApplicationException.class)
    public void testAddProductToCartAndProductAlreadyExists() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        when(cartService.addProductToCart(cartId, productId)).thenThrow(new PosApplicationException("Product can not be added. Product: " + productId + " already exists in the cart: " + cartId));
        cartController.addProductToCart(cartId, productId);
    }

    @Test
    public void testRemoveProductFromCartSuccessful() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        Cart expected = getCart(cartId);
        //expected.getProductOrders().get(0).getProduct().setId(productId);
        when(cartService.removeProductFromCart(cartId, productId)).thenReturn(expected);
        Cart returned = cartController.removeProductFromCart(cartId, productId);
        Optional<ProductOrder> productOrder = returned.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();
        assertFalse(productOrder.isPresent());
    }

    @Test(expected = PosApplicationException.class)
    public void testRemoveProductFromCartAndProductDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        when(cartService.removeProductFromCart(cartId, productId)).thenThrow(new PosApplicationException("Product count can not be updated. Product: " + productId + " does not exist in the cart: " + cartId));
        cartController.removeProductFromCart(cartId, productId);
    }

    @Test(expected = PosApplicationException.class)
    public void testRemoveProductFromCartAndCartDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        when(cartService.addProductToCart(cartId, productId)).thenThrow(new PosApplicationException("Cart: " + cartId + " does not exist"));
        cartController.addProductToCart(cartId, productId);
    }

    @Test
    public void testupdateProductCountInCartSuccessful() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        long itemCount = 2l;
        Cart expected = getCart(cartId);
        when(cartService.updateProductCountInCart(cartId, productId, itemCount)).thenReturn(expected);
        Cart returned = cartController.updateProductCountInCart(cartId, productId, itemCount);
        ProductOrder productOrder = returned.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst().get();
        assertEquals(itemCount, productOrder.getCount());
    }

    @Test(expected = PosApplicationException.class)
    public void testupdateProductCountInCartAndProductDoesNotExistInCart() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        long itemCount = 2l;
        when(cartService.updateProductCountInCart(cartId, productId, itemCount)).thenThrow(new PosApplicationException("Product count can not be updated. Product: " + productId + " does not exist in the cart: " + cartId));
        cartController.updateProductCountInCart(cartId, productId, itemCount);
    }

    @Test(expected = PosApplicationException.class)
    public void testupdateProductCountInCartAndCartDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        long itemCount = 2l;
        when(cartService.updateProductCountInCart(cartId, productId, itemCount)).thenThrow(new PosApplicationException("Cart: " + cartId + " does not exist"));
        cartController.updateProductCountInCart(cartId, productId, itemCount);
    }

    @Test
    public void testGetCart() throws Exception {
        long cartId = 1l;
        Cart expected = getCart(cartId);
        when(cartService.getCart(cartId)).thenReturn(expected);
        Cart returned = cartController.getCart(cartId);
        assertEquals(expected, returned);
    }

    private Cart getCart(long cartId) {
        Territory bengaluruTerritory = new Territory(10, "Bengaluru", "Bengaluru Territory");
        Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");
        Product sandwichMaker = new Product("Sandwich Maker", "Black and Decker Sandwich Maker", 1000, applianceCareCategory);
        ProductOrder sandwichMakerProductOrder = new ProductOrder(sandwichMaker, 2);
        List<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(sandwichMakerProductOrder);

        Cart cart = new Cart(bengaluruTerritory, productOrderList);
        cart.setId(cartId);
        return cart;
    }
}
