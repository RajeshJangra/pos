package com.altisource.pos.controller;

import com.altisource.pos.domain.Product;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/products", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public Product getUser(@PathVariable long id) throws PosApplicationException {
        return productService.getproduct(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createProduct(@RequestBody Product product) throws PosApplicationException {
        productService.createProduct(product);
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(OK)
    public void updateProduct(@RequestBody Product product) throws PosApplicationException {
        productService.updateProduct(product);
    }
}
