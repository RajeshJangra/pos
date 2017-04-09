package com.altisource.pos.controller;

import com.altisource.pos.domain.Category;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.service.CategoryService;
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
    public Category getUser(@PathVariable long id) throws PosApplicationException {
        return categoryService.getcategory(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createCategory(@RequestBody Category category) throws PosApplicationException {
        categoryService.createCategory(category);
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(OK)
    public void updateCategory(@RequestBody Category category) throws PosApplicationException {
        categoryService.updateCategory(category);
    }
}
