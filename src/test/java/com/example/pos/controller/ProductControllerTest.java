package com.example.pos.controller;

import com.example.pos.domain.Category;
import com.example.pos.domain.Product;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * BillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 10, 2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    @Test
    public void testCreateProductSuccessful() throws Exception {
        long productId = 1L;
        Product expected = getProduct(productId);
        when(productService.createProduct(expected)).thenReturn(expected);
        Product returned = productController.createProduct(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateProductAndProductAlreadyExists() throws Exception {
        long productId = 1L;
        Product expected = getProduct(productId);
        when(productService.createProduct(expected)).thenThrow(new PosApplicationException("Product: " + expected.getId() + " already exists"));
        productController.createProduct(expected);
    }

    @Test
    public void testUpdateProductSuccessful() throws Exception {
        long productId = 1L;
        Product expected = getProduct(productId);
        when(productService.updateProduct(expected)).thenReturn(expected);
        Product returned = productController.updateProduct(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testUpdateProductAndProductAlreadyExists() throws Exception {
        long productId = 1L;
        Product expected = getProduct(productId);
        when(productService.updateProduct(expected)).thenThrow(new PosApplicationException("Product: " + expected.getId() + " already exists"));
        productController.updateProduct(expected);
    }

    @Test
    public void testGetProductSuccessful() throws Exception {
        long productId = 1L;
        Product expected = getProduct(productId);
        when(productService.getProduct(expected.getId())).thenReturn(expected);
        Product returned = productController.getProduct(expected.getId());
        assertEquals(expected, returned);
    }

    private Product getProduct(long productId) {
        Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");

        Product sandwichMaker = new Product("Sandwich Maker", "Black and Decker Sandwich Maker", 1000, applianceCareCategory);
        sandwichMaker.setId(productId);
        return sandwichMaker;
    }
}
