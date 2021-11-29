package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private String quickDesc;
    private String fittingInfo;
    private String washInfo;
    private String material;
    private String description;
    private long productCategory;
    private long season;
    private long color;
    private double retailPrice;
    private boolean dryClean;
    private ProductMeasurementDTO measurements;
    private ImageDTO imgCover;
    private List<ImageDTO> images;
    private List<SizeDTO> sizes;
    private List<ProductOccasion> occasions;
}