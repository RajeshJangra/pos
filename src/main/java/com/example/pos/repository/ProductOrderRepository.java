package com.example.pos.repository;

import com.example.pos.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeshkumar on 08/04/17.
 */
@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
