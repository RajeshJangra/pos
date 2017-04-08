package com.altisource.pos.repository;

import com.altisource.pos.domain.Bill;
import com.altisource.pos.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeshkumar on 08/04/17.
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
