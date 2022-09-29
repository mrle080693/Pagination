package com.prostosasha.pagination.service;

import com.prostosasha.pagination.entity.Product;
import com.prostosasha.pagination.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return productRepository.save(product);
    }

    public List<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> allProducts = productRepository.findAll(pageable);

        return allProducts.getContent();
    }

    public List<Product> findAllByPrice(double price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByPrice(price, pageable);
    }

    public List<Product> findAllSorted(int page, int size, String paramNameForSort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(paramNameForSort));
        Page<Product> allProducts = productRepository.findAll(pageable);

        return allProducts.getContent();
    }
}
