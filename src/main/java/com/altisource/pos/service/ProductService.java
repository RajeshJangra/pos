package com.altisource.pos.service;

import com.altisource.pos.domain.Product;
import com.altisource.pos.exception.ProductValidationException;
import com.altisource.pos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createProduct(final Product product) throws ProductValidationException {
        if (productRepository.exists(product.getId())) {
            throw new ProductValidationException("Product already exists");
        }
        productRepository.save(product);
    }

    public void updateProduct(final Product product) throws ProductValidationException {
        if (productRepository.exists(product.getId())) {
            productRepository.save(product);
        }
        throw new ProductValidationException("Product does not exist");
    }

    public void deleteProduct(final long id) throws ProductValidationException {
        if (productRepository.exists(id)) {
            productRepository.delete(id);
        }
        throw new ProductValidationException("Product does not exist");
    }

    public Product getproduct(final long id) {
        return productRepository.findOne(id);
    }
}
