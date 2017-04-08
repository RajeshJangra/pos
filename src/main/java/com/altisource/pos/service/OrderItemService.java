package com.altisource.pos.service;

import com.altisource.pos.domain.OrderItem;
import com.altisource.pos.exception.OrderItemValidationException;
import com.altisource.pos.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<OrderItem> getItems() {
        return orderItemRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createOrderItem(final OrderItem orderItem) throws OrderItemValidationException {
        if (orderItemRepository.exists(orderItem.getId())) {
            throw new OrderItemValidationException("Item already exists");
        }
        orderItemRepository.save(orderItem);
    }

    public void updateOrderItem(final OrderItem orderItem) throws OrderItemValidationException {
        if (orderItemRepository.exists(orderItem.getId())) {
            orderItemRepository.save(orderItem);
        }
        throw new OrderItemValidationException("Item does not exist");
    }

    public void deleteOrderItem(final long id) throws OrderItemValidationException {
        if (orderItemRepository.exists(id)) {
            orderItemRepository.delete(id);
        }
        throw new OrderItemValidationException("Item does not exist");
    }

    public OrderItem getOrderItem(final long id) {
        return orderItemRepository.findOne(id);
    }
}
