package com.prostosasha.pagination.controller;

import com.prostosasha.pagination.entity.Product;
import com.prostosasha.pagination.exception.ValidationException;
import com.prostosasha.pagination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{page}/{size}")
    public List<Product> findAll(@PathVariable int page, @PathVariable int size) {
        return productService.findAll(page, size);
    }

    @GetMapping("/{price}/{page}/{size}")
    public List<Product> getAllByPrice(@PathVariable double price, @PathVariable int page, @PathVariable int size) {
        return productService.findAllByPrice(price, page, size);
    }

    @GetMapping("/getAllSorted/{page}/{size}/{paramNameForSort}")
    public List<Product> getAllSorted
            (@PathVariable int page, @PathVariable int size, @PathVariable String paramNameForSort)
            throws ValidationException {
        return productService.findAllSorted(page, size, paramNameForSort);
    }
}
