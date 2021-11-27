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

    private IProductRepository ProductRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.ProductRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return ProductRepository.findById(id);
    }

    @Override
    public Product save(ProductDTO productDTO) {
        Product entity = ProductDTOMapper.toEntity(productDTO);
        return ProductRepository.save(entity);
    }

    @Override
    public Iterable<Product> findAll() {
        return ProductRepository.findAll();
    }


}
