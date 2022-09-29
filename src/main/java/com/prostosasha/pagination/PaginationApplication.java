package com.prostosasha.pagination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class PaginationApplication {

    Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

    public static void main(String[] args) {
        SpringApplication.run(PaginationApplication.class, args);
    }

}
