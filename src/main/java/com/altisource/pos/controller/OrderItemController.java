package com.altisource.pos.controller;

import com.altisource.pos.domain.OrderItem;
import com.altisource.pos.exception.OrderItemValidationException;
import com.altisource.pos.service.OrderItemService;
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
@RequestMapping(value = "/orderorderItems", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class OrderItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemController.class);
    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OrderItem getUser(@PathVariable long id) throws OrderItemValidationException {
        return orderItemService.getOrderItem(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderItem(@RequestBody OrderItem orderItem) throws OrderItemValidationException {
        orderItemService.createOrderItem(orderItem);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderItem(@RequestBody OrderItem orderItem) throws OrderItemValidationException {
        orderItemService.updateOrderItem(orderItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteOrderItem(@PathVariable long id) throws OrderItemValidationException {
        orderItemService.deleteOrderItem(id);
    }


    @ExceptionHandler(OrderItemValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(OrderItemValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
