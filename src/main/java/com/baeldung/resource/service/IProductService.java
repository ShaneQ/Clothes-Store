package com.baeldung.resource.service;

import com.baeldung.resource.persistence.model.Product;

import com.baeldung.resource.persistence.model.ProductInventoryStatus;
import com.baeldung.resource.web.dto.ProductDTO;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface IProductService {

    Optional<Product> findById(Long id);

    Product update(ProductDTO foo);

    Product create(ProductDTO productDTO);

    Iterable<Product> findAll();

    List<Product> findAllActive();

    void toggleHidden(Long id, boolean b);

    void delete(long id);

    List<Product> findAllWithFilters(Map<String, String> filters);

    void updateInventoryStatus(Long productId, Long inventoryId, ProductInventoryStatus status);
}
