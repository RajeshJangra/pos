package com.altisource.pos.service;

import com.altisource.pos.domain.Category;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(final Category category) throws PosApplicationException {
        if (categoryRepository.exists(category.getId())) {
            String message = "Category: " + category.getId() + " already exists";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        categoryRepository.save(category);
    }

    public void updateCategory(final Category category) throws PosApplicationException {
        if (categoryRepository.exists(category.getId())) {
            categoryRepository.save(category);
        }
        String message = "Category: " + category.getId() + " does not exist";
        LOGGER.error(message);
        throw new PosApplicationException(message);
    }

    public Category getcategory(final long id) {
        return categoryRepository.findOne(id);
    }
}
