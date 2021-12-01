package com.baeldung.resource.persistence.repository;

import com.baeldung.resource.persistence.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {

    Iterable<Product> findByDeletedFalse();
}
