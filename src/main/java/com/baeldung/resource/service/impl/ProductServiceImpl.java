package com.baeldung.resource.service.impl;

import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.persistence.repository.IProductRepository;
import com.baeldung.resource.service.IProductService;
import com.baeldung.resource.web.dto.ProductDTO;
import com.baeldung.resource.web.mappers.ProductDTOMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Product save(ProductDTO productDTO) {
        Product entity = ProductDTOMapper.toEntity(productDTO);
        return repository.save(entity);
    }

    @Override
    public Iterable<Product> findAll() {
        return repository.findByDeletedFalse();
    }

    @Override
    public void toggleHidden(Long id, boolean hidden) {
        Optional<Product> product = this.repository.findById(id);
        if(product.isPresent()){
            product.get().setHidden(hidden);
            this.repository.save(product.get());
        }
    }

    @Override
    public void delete(long id) {
        Optional<Product> product = this.repository.findById(id);
        if(product.isPresent()){
            product.get().setDeleted(true);
            this.repository.save(product.get());
        }
    }
}
