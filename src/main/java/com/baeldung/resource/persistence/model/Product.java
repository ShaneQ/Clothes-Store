package com.baeldung.resource.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean dryClean;

    private String quickDesc;

    private String material;

    private String description;

    private String fittingInfo;
    private String washInfo;

    private double retailPrice;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_product_category")
    private ProductCategory category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_season")
    private Season season;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_color")
    private Color color;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProductMeasurement measurement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cover_img")
    private Image imgCover;

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ProductImage> images;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private List<ProductOccasion> occasions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private List<ProductSize> sizes;

}
