package com.altisource.pos.repository;

import com.altisource.pos.domain.Item;
import com.altisource.pos.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeshkumar on 08/04/17.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
