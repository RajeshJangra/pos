package com.altisource.pos.service;

import com.altisource.pos.domain.*;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.CartRepository;
import com.altisource.pos.repository.ProductRepository;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * BillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 10, 2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @InjectMocks
    CartService cartService;
    @Mock
    CartRepository cartRepository;
    @Mock
    ProductRepository productRepository;


    @Test
    public void testCreateCartSuccessful() throws Exception {
        long cartId = 1l;
        Cart expected = getCart(cartId);
        when(cartRepository.exists(expected.getId())).thenReturn(false);
        when(cartRepository.save(expected)).thenReturn(expected);
        Cart returned = cartService.createCart(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateCartAndCartAlreadyExists() throws Exception {
        long cartId = 1l;
        Cart expected = getCart(cartId);
        when(cartRepository.exists(expected.getId())).thenReturn(true);
        cartService.createCart(expected);
    }

    @Test
    public void testAddProductToCartSuccessful() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        Cart expected = getCart(cartId);
        when(cartRepository.findOne(cartId)).thenReturn(expected);
        when(cartRepository.save(expected)).thenReturn(expected);
        when(productRepository.findOne(productId)).thenReturn(getProduct(productId));
        Cart returned = cartService.addProductToCart(cartId, productId);
        Optional<ProductOrder> productOrder = returned.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();
        assertEquals(1l, productOrder.get().getProduct().getId());
    }

    @Test(expected = PosApplicationException.class)
    public void testAddProductToCartAndCartDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        when(cartRepository.findOne(cartId)).thenReturn(null);
        cartService.addProductToCart(cartId, productId);
    }

    @Test(expected = PosApplicationException.class)
    public void testAddProductToCartAndProductAlreadyExists() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        Cart expected = getCart(cartId);
        when(cartRepository.findOne(cartId)).thenReturn(expected);
        when(cartRepository.save(expected)).thenReturn(expected);
        cartService.addProductToCart(cartId, productId);
    }

    @Test
    public void testRemoveProductFromCartSuccessful() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        Cart expected = getCart(cartId);
        when(cartRepository.findOne(cartId)).thenReturn(expected);
        when(cartRepository.save(expected)).thenReturn(expected);
        Cart returned = cartService.removeProductFromCart(cartId, productId);
        Optional<ProductOrder> productOrder = returned.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst();
        assertFalse(productOrder.isPresent());
    }

    @Test(expected = PosApplicationException.class)
    public void testRemoveProductFromCartAndProductDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        Cart expected = getCart(cartId);
        when(cartRepository.findOne(cartId)).thenReturn(expected);
        when(cartRepository.save(expected)).thenReturn(expected);
        cartService.removeProductFromCart(cartId, productId);
    }

    @Test(expected = PosApplicationException.class)
    public void testRemoveProductFromCartAndCartDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        when(cartRepository.findOne(cartId)).thenReturn(null);
        cartService.addProductToCart(cartId, productId);
    }

    @Test
    public void testupdateProductCountInCartSuccessful() throws Exception {
        long cartId = 1l;
        long productId = 0l;
        long itemCount = 2l;
        Cart expected = getCart(cartId);
        when(cartRepository.findOne(cartId)).thenReturn(expected);
        when(cartRepository.save(expected)).thenReturn(expected);
        Cart returned = cartService.updateProductCountInCart(cartId, productId, itemCount);
        ProductOrder productOrder = returned.getProductOrders().stream().filter(orderProduct -> orderProduct.getProduct().getId() == productId).findFirst().get();
        assertEquals(itemCount, productOrder.getCount());
    }

    @Test(expected = PosApplicationException.class)
    public void testupdateProductCountInCartAndProductDoesNotExistInCart() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        long itemCount = 2l;
        Cart expected = getCart(cartId);
        when(cartRepository.findOne(cartId)).thenReturn(expected);
        when(cartRepository.save(expected)).thenReturn(expected);
        cartService.updateProductCountInCart(cartId, productId, itemCount);
    }

    @Test(expected = PosApplicationException.class)
    public void testupdateProductCountInCartAndCartDoesNotExist() throws Exception {
        long cartId = 1l;
        long productId = 1l;
        long itemCount = 2l;
        when(cartRepository.findOne(cartId)).thenReturn(null);
        cartService.updateProductCountInCart(cartId, productId, itemCount);
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

    private Product getProduct(long productId){
        Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");
        Product sandwichMaker = new Product("Sandwich Maker", "Black and Decker Sandwich Maker", 1000, applianceCareCategory);
        sandwichMaker.setId(productId);
        return sandwichMaker;
    }
}
