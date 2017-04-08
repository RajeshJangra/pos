package com.altisource.pos.controller;

import com.altisource.pos.domain.Product;
import com.altisource.pos.exception.ProductValidationException;
import com.altisource.pos.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/products", produces = "application/json", consumes = "application/json")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getUser(@PathVariable long id) throws ProductValidationException {
        return productService.getproduct(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) throws ProductValidationException {
        productService.createProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product) throws ProductValidationException {
        productService.updateProduct(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteProduct(@PathVariable long id) throws ProductValidationException {
        productService.deleteProduct(id);
    }


    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(ProductValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
