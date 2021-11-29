package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.service.IProductService;
import com.baeldung.resource.web.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/private/product")
public class ProductAdminController {

    private IProductService productService;

    public ProductAdminController(IProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody ProductDTO dto) {
        Product savedProduct = this.productService.save(dto);

        log.info("Product Created with id:{}", savedProduct.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public void update(@RequestBody ProductDTO dto) {
        Product savedProduct = this.productService.save(dto);

        log.info("Product Updated with id:{}", savedProduct.getId());
    }


}
