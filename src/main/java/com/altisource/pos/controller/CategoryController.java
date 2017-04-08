package com.altisource.pos.controller;

import com.altisource.pos.domain.Category;
import com.altisource.pos.exception.CategoryValidationException;
import com.altisource.pos.service.CategoryService;
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
@RequestMapping(value = "/categories", produces = "application/json", consumes = "application/json")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Category getUser(@PathVariable long id) throws CategoryValidationException {
        return categoryService.getcategory(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category) throws CategoryValidationException {
        categoryService.createCategory(category);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@RequestBody Category category) throws CategoryValidationException {
        categoryService.updateCategory(category);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCategory(@PathVariable long id) throws CategoryValidationException {
        categoryService.deleteCategory(id);
    }


    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(CategoryValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
