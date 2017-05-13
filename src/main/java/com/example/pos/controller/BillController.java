package com.example.pos.controller;

import com.example.pos.domain.Bill;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by rajeshkumar on 07/04/17.
 */
@RestController
@RequestMapping(value = "/bills", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public Page<Bill> getBills(@RequestParam int pageNumber, @RequestParam String sortField, @RequestParam String sortDirection, @RequestParam(required = false) Date billDate, @RequestParam(required = false) Long locationCode, @RequestParam(required = false) Double totalBillAmount) throws PosApplicationException {
        return billService.getBills(billDate, locationCode, totalBillAmount, pageNumber, sortField, sortDirection);
    }

    @RequestMapping(value = "/carts/{cartId}", method = POST)
    @ResponseStatus(CREATED)
    public Bill createBill(@PathVariable long cartId) throws PosApplicationException {
        return billService.createBill(cartId);
    }
}
