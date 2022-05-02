package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.ProductInventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInventoryDTO {

    private ProductDTO product;
    private ProductInventoryStatus status;
    private Long id;
    private Long id_size;

}
