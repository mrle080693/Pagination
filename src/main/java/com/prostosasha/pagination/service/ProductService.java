package com.prostosasha.pagination.service;

import com.prostosasha.pagination.entity.Product;
import com.prostosasha.pagination.exception.ValidationException;
import com.prostosasha.pagination.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        try {
            return productRepository.save(product);
        } catch (IllegalArgumentException e) {
            throw new ValidationException();
        }
    }

    public List<Product> findAll(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> allProducts = productRepository.findAll(pageable);

            return allProducts.getContent();
        } catch (IllegalArgumentException e) {
            throw new ValidationException();
        }
    }

    public List<Product> findAllByPrice(double price, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return productRepository.findAllByPrice(price, pageable);

        } catch (IllegalArgumentException e) {
            throw new ValidationException();
        }
    }

    public List<Product> findAllSorted(int page, int size, String paramNameForSort) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(paramNameForSort));
            Page<Product> allProducts = productRepository.findAll(pageable);

            return allProducts.getContent();
        } catch (PropertyReferenceException | IllegalArgumentException e) {
            throw new ValidationException();
        }
    }
}
