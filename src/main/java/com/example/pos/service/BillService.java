package com.example.pos.service;

import com.example.pos.domain.Bill;
import com.example.pos.domain.Cart;
import com.example.pos.exception.PosApplicationException;
import com.example.pos.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.pos.util.Constant.PAGE_SIZE;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class BillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillService.class);

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CartService cartService;

    public Page<Bill> getBills(Date billDate, Long locationCode, Double totalBillAmount, int pageNumber, final String sortField, final String sortDirectionString) throws PosApplicationException {
        Sort.Direction sortDirection = Sort.Direction.fromString(sortDirectionString);
        validateSortField(sortField);
        return billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(billDate, locationCode, totalBillAmount, new PageRequest(pageNumber, PAGE_SIZE, new Sort(sortDirection, sortField)));
    }

    private void validateSortField(final String sortField) throws PosApplicationException {
        if (!(sortField.equals("locationCode") || sortField.equals("totalBillAmount") || sortField.equals("billDate"))) {
            String message = "Page can only be sorted by locationCode, billAmount or billDate";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
    }

    public Bill createBill(final long cartId) throws PosApplicationException {
        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            String message = "Input Cart:" + cartId + " does not exist";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        Bill bill = new Bill.Builder().cart(cart).build();
        return billRepository.save(bill);
    }
}
