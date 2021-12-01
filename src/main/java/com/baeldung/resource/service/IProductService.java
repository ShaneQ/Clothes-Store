package com.baeldung.resource.service;

import com.baeldung.resource.persistence.model.Product;

import com.baeldung.resource.web.dto.ProductDTO;
import java.util.Optional;


public interface IProductService {

    Optional<Product> findById(Long id);

    Product save(ProductDTO foo);

    Iterable<Product> findAll();

    void toggleHidden(Long id, boolean b);

    void delete(long id);
}
