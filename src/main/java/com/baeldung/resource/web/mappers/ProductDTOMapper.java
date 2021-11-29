package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.Color;
import com.baeldung.resource.persistence.model.Image;
import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.persistence.model.ProductCategory;
import com.baeldung.resource.persistence.model.ProductImage;
import com.baeldung.resource.persistence.model.ProductMeasurement;
import com.baeldung.resource.persistence.model.ProductSize;
import com.baeldung.resource.persistence.model.Season;
import com.baeldung.resource.persistence.model.Size;
import com.baeldung.resource.web.dto.ProductMeasurementDTO;
import com.baeldung.resource.web.dto.ImageDTO;
import com.baeldung.resource.web.dto.ProductDTO;
import com.baeldung.resource.web.dto.SizeDTO;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductDTOMapper {

    public static Product toEntity(ProductDTO dto) {
        Product product = Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .dryClean(dto.isDryClean())
                .quickDesc(dto.getQuickDesc())
                .material(dto.getMaterial())
                .description(dto.getDescription())
                .fittingInfo(dto.getFittingInfo())
                .washInfo(dto.getWashInfo())
                .season(new Season(dto.getSeason(), null))
                .color(new Color(dto.getColor(), null))
                .category(new ProductCategory(dto.getProductCategory(), null))
                .retailPrice(dto.getRetailPrice()).build();
        ProductMeasurement measurement = ProductMeasurement.builder()
                .chest(dto.getMeasurements().getChest())
                .hips(dto.getMeasurements().getHips())
                .length(dto.getMeasurements().getLength())
                .waist(dto.getMeasurements().getWaist())
                .id(dto.getId()).build();

        product.setMeasurement(measurement);
        ArrayList<ProductSize> productSizes = new ArrayList<>();
        for (SizeDTO size : dto.getSizes()) {
            ProductSize productSize = new ProductSize();
            productSize.setSize(new Size(size.getId(), null));
            productSize.setId_product(product.getId());
            productSizes.add(productSize);
        }
        Image coverImage = new Image();
        coverImage.setPath(dto.getImgCover().getUrl());
        product.setImgCover(coverImage);
        product.setSizes(productSizes);
        if (dto.getImages() != null) {
            ArrayList<ProductImage> productImages = new ArrayList<>();
            for (ImageDTO imageDTO : dto.getImages()) {
                ProductImage productImage = new ProductImage();
                Image image = new Image();
                image.setPath(imageDTO.getUrl());
                productImage.setId_image(image);
                productImages.add(productImage);
            }
            product.setImages(productImages);
        }

        return product;
    }

    public static ProductDTO convertToDto(Product entity) {
        ProductDTO dto = ProductDTO.builder()
                .description(entity.getDescription())
                .dryClean(entity.isDryClean())
                .id(entity.getId())
                .fittingInfo(entity.getFittingInfo())
                .imgCover(new ImageDTO(entity.getImgCover().getId(), entity.getImgCover().getPath()))
                .quickDesc(entity.getQuickDesc())
                .material(entity.getMaterial())
                .washInfo(entity.getWashInfo())
                .name(entity.getName())
                .color(entity.getColor().getId())
                .season(entity.getSeason().getId())
                .productCategory(entity.getCategory().getId())
                .retailPrice(entity.getRetailPrice())
                .build();
        dto.setImages(entity.getImages().stream().map(entityImage -> new ImageDTO(entityImage.getId_image().getId(), entityImage.getId_image().getPath())).collect(
                Collectors.toList()));
        dto.setMeasurements(new ProductMeasurementDTO(entity.getMeasurement().getLength(),entity.getMeasurement().getChest(),entity.getMeasurement().getHips(),entity.getMeasurement().getWaist()));
        dto.setSizes(entity.getSizes().stream().map( entitySize -> new SizeDTO(entitySize.getSize().getId(), entitySize.getSize().getName())).collect(Collectors.toList()));
        return dto;
    }
}
