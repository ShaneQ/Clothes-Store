package com.baeldung.resource.web.controller;

import static com.baeldung.resource.web.dto.Filters.FILTER_BY_CATEGORY;
import static com.baeldung.resource.web.dto.Filters.FILTER_BY_COLOR;
import static com.baeldung.resource.web.dto.Filters.FILTER_BY_NAME;
import static com.baeldung.resource.web.dto.Filters.FILTER_BY_SEASON;
import static com.baeldung.resource.web.dto.Filters.FILTER_BY_SIZE;
import static java.util.Objects.nonNull;

import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.service.IProductService;
import com.baeldung.resource.web.dto.ProductDTO;
import com.baeldung.resource.web.mappers.ProductDTOMapper;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.QueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/public/product")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<ProductDTO> findAll() {
        Iterable<Product> products = this.productService.findAllActive();
        List<ProductDTO> dtos = new ArrayList<>();
        products.forEach(p -> dtos.add(ProductDTOMapper.convertToDto(p)));
        return dtos;
    }

    @GetMapping("/query")
    public Collection<ProductDTO> search(@QueryParam(FILTER_BY_SIZE) String filterBySize,
            @QueryParam(FILTER_BY_COLOR) String filterByColor, @QueryParam(FILTER_BY_SEASON) String filterBySeason,
            @QueryParam(FILTER_BY_CATEGORY) String filterByCategory, @QueryParam(FILTER_BY_NAME) String filterByName) {
        Map<String, String> filters = new HashMap<>();
        if (nonNull(filterBySize)) {
            filters.put(FILTER_BY_SIZE, filterBySize);
        }
        if (nonNull(filterByColor)) {
            filters.put(FILTER_BY_COLOR, filterByColor);
        }
        if (nonNull(filterBySeason)) {
            filters.put(FILTER_BY_SEASON, filterBySeason);
        }
        if (nonNull(filterBySeason)) {
            filters.put(FILTER_BY_SEASON, filterBySeason);
        }
        if (nonNull(filterByCategory)) {
            filters.put(FILTER_BY_CATEGORY, filterByCategory);
        }
        if (nonNull(filterByName)) {
            filters.put(FILTER_BY_NAME, filterByName);
        }
        Iterable<Product> products = this.productService.findAllWithFilters(filters);
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
