package com.altisource.pos.service;

import com.altisource.pos.domain.Bill;
import com.altisource.pos.exception.BillValidationException;
import com.altisource.pos.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class BillService {

    @Autowired
    BillRepository billRepository;

    public List<Bill> getItems() {
        return billRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createBill(final Bill bill) throws BillValidationException {
        if (billRepository.exists(bill.getId())) {
            throw new BillValidationException("Item already exists");
        }
        billRepository.save(bill);
    }

    public void updateBill(final Bill bill) throws BillValidationException {
        if (billRepository.exists(bill.getId())) {
            billRepository.save(bill);
        }
        throw new BillValidationException("Item does not exist");
    }

    public void deleteBill(final long id) throws BillValidationException {
        if (billRepository.exists(id)) {
            billRepository.delete(id);
        }
        throw new BillValidationException("Item does not exist");
    }

    public Bill getBill(final long id) {
        return billRepository.findOne(id);
    }
}
