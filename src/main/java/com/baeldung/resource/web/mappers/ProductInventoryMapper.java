package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.persistence.model.ProductInventoryStatus;
import com.baeldung.resource.persistence.model.ProductInventory;
import com.baeldung.resource.web.dto.ProductInventoryDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductInventoryMapper {

    public static ProductInventoryDTO convertToDto(ProductInventory size, Product p) {
        return ProductInventoryDTO.builder().product(ProductDTOMapper.convertToDto(p)).id(size.getId())
                .id_size(size.getSize().getId()).status(
                        ProductInventoryStatus.valueOf(size.getStatus())).build();
    }
}
