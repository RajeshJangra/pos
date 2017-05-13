package com.example.pos.controller;

import com.example.pos.domain.Category;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.service.CategoryService;
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
@RequestMapping(value = "/categories", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public Category getCategory(@PathVariable long id) throws PosApplicationException {
        return categoryService.getCategory(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Category createCategory(@RequestBody Category category) throws PosApplicationException {
        return categoryService.createCategory(category);
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(OK)
    public Category updateCategory(@RequestBody Category category) throws PosApplicationException {
        return categoryService.updateCategory(category);
    }
}
