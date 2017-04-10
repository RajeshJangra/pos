package com.altisource.pos.repository;

import com.altisource.pos.domain.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by rajeshkumar on 08/04/17.
 */
@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {

    Page<Bill> findByBillDateOrLocationCodeOrTotalBillAmount(Date billDate, Long locationCode, Double totalBillAmount, Pageable pageable);

/*    List<Bill> findByLocationCode(Date billDate, Pageable pageable);

    List<Bill> findByTotalBillAmount(Date billDate, Pageable pageable);*/
}
