package com.altisource.pos.service;

import com.altisource.pos.domain.Product;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(final Product product) throws PosApplicationException {
        if (productRepository.exists(product.getId())) {
            String message = "Product: " + product.getId() + " already exists";
            LOGGER.error(message);
            throw new PosApplicationException(message);
        }
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product) throws PosApplicationException {
        if (productRepository.exists(product.getId())) {
            return productRepository.save(product);
        }
        String message = "Product: " + product.getId() + " does not exist";
        LOGGER.error(message);
        throw new PosApplicationException(message);
    }

    public Product getProduct(final long id) {
        return productRepository.findOne(id);
    }
}
