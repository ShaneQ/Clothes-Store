package com.baeldung.resource.service.impl;

import com.baeldung.resource.exceptions.ResourceNotFound;
import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.persistence.model.ProductInventory;
import com.baeldung.resource.persistence.model.ProductInventoryStatus;
import com.baeldung.resource.persistence.repository.IProductRepository;
import com.baeldung.resource.service.IProductService;
import com.baeldung.resource.web.dto.Filters;
import com.baeldung.resource.web.dto.ProductDTO;
import com.baeldung.resource.web.mappers.ProductDTOMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository repository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Product update(ProductDTO productDTO) {
        Product entity = findById(productDTO.getId()).orElseThrow(() -> new ResourceNotFound("No Product found"));
        Product newEntity = ProductDTOMapper.toEntity(productDTO);
        List<ProductInventory> sizes = entity.getSizes();
        newEntity.getSizes().forEach(size -> {
            if (size.getId() == null) {
                size.setStatus(ProductInventoryStatus.STORED.toString());
                sizes.add(size);
            }
        });
        newEntity.setSizes(sizes);
        return repository.save(newEntity);
    }

    @Override
    public Product create(ProductDTO productDTO) {
        Product newEntity = ProductDTOMapper.toEntity(productDTO);
        List<ProductInventory> sizes = new ArrayList<>();
        newEntity.getSizes().forEach(size -> {
            if (size.getId() == null) {
                size.setStatus(ProductInventoryStatus.STORED.toString());
                sizes.add(size);
            }
        });
        newEntity.setSizes(sizes);
        newEntity.setHidden(true);
        return repository.save(newEntity);
    }

    @Override
    public List<Product> findAll() {
        return repository.findByDeletedFalseAndHiddenFalse();
    }

    @Override
    public List<Product> findAllActive() {
        return repository.findByDeletedFalseAndHiddenFalse();
    }

    @Override
    public void toggleHidden(Long id, boolean hidden) {
        Optional<Product> product = this.repository.findById(id);
        if (product.isPresent()) {
            product.get().setHidden(hidden);
            this.repository.save(product.get());
        }
    }

    @Override
    public void delete(long id) {
        Optional<Product> product = this.repository.findById(id);
        if (product.isPresent()) {
            product.get().setDeleted(true);
            this.repository.save(product.get());
        }
    }

    @Override
    public List<Product> findAllWithFilters(Map<String, String> filters) {
        return findAll().stream()
                .filter(product -> Filters.filterBySize(product, filters))
                .filter(product -> Filters.filterByColor(product, filters))
                .filter(product -> Filters.filterBySeason(product, filters))
                .filter(product -> Filters.filterByCategory(product, filters))
                .filter(product -> Filters.filterByName(product, filters))
                .collect(Collectors.toList());
    }

    @Override
    public void updateInventoryStatus(Long productId, Long inventoryId, ProductInventoryStatus status) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound("Product not found"));
        product.getSizes().forEach(size -> {
                    if (size.getId().equals(inventoryId)) {
                        size.setStatus(status.name());
                    }
                }
        );
        repository.save(product);
    }
}
