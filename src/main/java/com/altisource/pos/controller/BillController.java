package com.altisource.pos.controller;

import com.altisource.pos.domain.Bill;
import com.altisource.pos.exception.BillValidationException;
import com.altisource.pos.service.BillService;
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
@RequestMapping(value = "/bills", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class BillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Bill getUser(@PathVariable long id) throws BillValidationException {
        return billService.getBill(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBill(@RequestBody Bill bill) throws BillValidationException {
        billService.createBill(bill);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateBill(@RequestBody Bill bill) throws BillValidationException {
        billService.updateBill(bill);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteBill(@PathVariable long id) throws BillValidationException {
        billService.deleteBill(id);
    }


    @ExceptionHandler(BillValidationException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(BillValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
