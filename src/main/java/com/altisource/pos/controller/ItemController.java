package com.altisource.pos.controller;

import com.altisource.pos.domain.Item;
import com.altisource.pos.exception.ItemValidationException;
import com.altisource.pos.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/items", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Item getUser(@PathVariable long id) throws ItemValidationException {
        return itemService.getItem(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody Item item) throws ItemValidationException {
        itemService.createItem(item);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@RequestBody Item item) throws ItemValidationException {
        itemService.updateItem(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteItem(@PathVariable long id) throws ItemValidationException {
        itemService.deleteItem(id);
    }


    @ExceptionHandler(ItemValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(ItemValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
