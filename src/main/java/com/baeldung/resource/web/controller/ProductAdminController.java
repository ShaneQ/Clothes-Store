package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.persistence.model.ProductInventoryStatus;
import com.baeldung.resource.service.IProductService;
import com.baeldung.resource.web.dto.ProductDTO;
import com.baeldung.resource.web.dto.ProductInventoryDTO;
import com.baeldung.resource.web.mappers.ProductInventoryMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/product")
public class ProductAdminController {

    private IProductService productService;

    public ProductAdminController(IProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody ProductDTO dto) {
        Product savedProduct = this.productService.create(dto);

        log.info("Product Created with id:{}", savedProduct.getId());
        return savedProduct.getId();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Long update(@RequestBody ProductDTO dto) {
        Product savedProduct = this.productService.update(dto);

        log.info("Product Updated with id:{}", savedProduct.getId());
        return savedProduct.getId();
    }

    @GetMapping("/inventory")
    public Collection<ProductInventoryDTO> findAllInventory() {
        Iterable<Product> products = this.productService.findAll();
        List<ProductInventoryDTO> dtos = new ArrayList<>();
        products.forEach(p -> p.getSizes().forEach(size -> dtos.add(ProductInventoryMapper.convertToDto(size, p))));
        return dtos;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{productId}/inventory/{inventoryId}/{status}")
    public void updateInventoryStatus(@PathVariable Long productId, @PathVariable Long inventoryId,
            @PathVariable ProductInventoryStatus status) {
        log.info("Product Inventory Updated with inventory id:{}, status {}", inventoryId, status);
        productService.updateInventoryStatus(productId, inventoryId, status);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/hide/{id}")
    public void hide(@PathVariable long id) {
        this.productService.toggleHidden(id, true);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/unhide/{id}")
    public void unhide(@PathVariable long id) {
        this.productService.toggleHidden(id, false);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.productService.delete(id);
    }
}
