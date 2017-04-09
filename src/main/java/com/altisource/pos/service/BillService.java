package com.altisource.pos.service;

import com.altisource.pos.domain.Bill;
import com.altisource.pos.domain.Cart;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.altisource.pos.util.Constant.PAGE_SIZE;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class BillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillService.class);

    @Autowired
    BillRepository billRepository;

    @Autowired
    CartService cartService;

    public Page<Bill> getBills(int pageNumber, final String sortField, final String sortDirectionString) throws PosApplicationException {
        Sort.Direction sortDirection = Sort.Direction.fromString(sortDirectionString);
        validateSortField(sortField);
        return billRepository.findAll(new PageRequest(pageNumber, PAGE_SIZE, new Sort(sortDirection, sortField)));
    }

    private void validateSortField(final String sortField) throws PosApplicationException {
        if (!(sortField.equals("locationCode") || sortField.equals("billAmount") || sortField.equals("billDate"))) {
            String message = "Page can only be sorted by locationCode, billAmount or billDate";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
    }

    public void createBill(final long cartId) throws PosApplicationException {
        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            String message = "Input Cart:" + cartId + " does not exist";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        Bill bill = new Bill.Builder().cart(cart).build();
        billRepository.save(bill);
    }
}
