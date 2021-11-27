package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.service.IProductService;
import com.baeldung.resource.web.dto.ProductDTO;
import com.baeldung.resource.web.mappers.ProductDTOMapper;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException ;

@RestController
@RequestMapping(value = "/api/public/product")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<ProductDTO> findAll() {
        Iterable<Product> products = this.productService.findAll();
        List<ProductDTO> dtos = new ArrayList<>();
        products.forEach(p -> dtos.add(ProductDTOMapper.convertToDto(p)));
        return dtos;
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findOne(Principal principal, @PathVariable Long id) {
        Product entity = productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ProductDTOMapper.convertToDto(entity);
    }
}
