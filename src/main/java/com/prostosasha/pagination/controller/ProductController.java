package com.prostosasha.pagination.controller;

import com.prostosasha.pagination.entity.Product;
import com.prostosasha.pagination.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{page}/{size}")
    public List<Product> getAll(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> allProducts = productRepository.findAll(pageable);

        return allProducts.getContent();
    }

    @GetMapping("/{price}/{page}/{size}")
    public List<Product> getAllByPrice(@PathVariable double price, @PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByPrice(price, pageable);
    }

    @GetMapping("/getAllSorted/{page}/{size}/{paramNameForSort}")
    public List<Product> getAllSorted(@PathVariable int page, @PathVariable int size, @PathVariable String paramNameForSort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(paramNameForSort));
        Page<Product> allProducts = productRepository.findAll(pageable);

        return allProducts.getContent();
    }
}
