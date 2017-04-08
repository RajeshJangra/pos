package com.altisource.pos.repository;

import com.altisource.pos.domain.Category;
import com.altisource.pos.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeshkumar on 08/04/17.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
