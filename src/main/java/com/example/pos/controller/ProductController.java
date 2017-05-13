package com.example.pos.controller;

import com.example.pos.domain.Product;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.service.ProductService;
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
    public Product getProduct(@PathVariable long id) throws PosApplicationException {
        return productService.getProduct(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Product createProduct(@RequestBody Product product) throws PosApplicationException {
        return productService.createProduct(product);
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(OK)
    public Product updateProduct(@RequestBody Product product) throws PosApplicationException {
        return productService.updateProduct(product);
    }
}
