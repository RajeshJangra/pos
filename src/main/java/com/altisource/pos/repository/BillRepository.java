package com.altisource.pos.repository;

import com.altisource.pos.domain.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeshkumar on 08/04/17.
 */
@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
}
