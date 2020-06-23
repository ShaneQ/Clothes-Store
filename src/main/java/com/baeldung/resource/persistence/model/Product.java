package com.baeldung.resource.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String type;

    @OneToOne
    @JoinColumn(name = "id_cover_img")
    private Image coverImg;

    @OneToMany
    @JoinColumn(name = "id_product")
    private List<ProductSize> sizes;

}
