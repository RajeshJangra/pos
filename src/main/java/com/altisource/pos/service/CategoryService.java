package com.altisource.pos.service;

import com.altisource.pos.domain.Category;
import com.altisource.pos.domain.Product;
import com.altisource.pos.exception.CategoryValidationException;
import com.altisource.pos.exception.ProductValidationException;
import com.altisource.pos.repository.CategoryRepository;
import com.altisource.pos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategorys() {
        return categoryRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createCategory(final Category category) throws CategoryValidationException {
        if (categoryRepository.exists(category.getId())) {
            throw new CategoryValidationException("Category already exists");
        }
        categoryRepository.save(category);
    }

    public void updateCategory(final Category category) throws CategoryValidationException {
        if (categoryRepository.exists(category.getId())) {
            categoryRepository.save(category);
        }
        throw new CategoryValidationException("Category does not exist");
    }

    public void deleteCategory(final long id) throws CategoryValidationException {
        if (categoryRepository.exists(id)) {
            categoryRepository.delete(id);
        }
        throw new CategoryValidationException("Category does not exist");
    }

    public Category getcategory(final long id) {
        return categoryRepository.findOne(id);
    }
}
