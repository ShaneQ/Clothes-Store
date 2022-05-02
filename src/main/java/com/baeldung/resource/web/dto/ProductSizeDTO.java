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
public class ProductSizeDTO {

    private Long id;

    private Long id_size;

    private ProductInventoryStatus status;

}