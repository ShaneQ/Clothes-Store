package com.baeldung.resource.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    @Column(nullable=true)
    private Integer length;

    @Column(nullable=true)
    @Nullable
    private Integer chest;

    @Column(nullable=true)
    @Nullable
    private Integer hips;

    @Column(nullable=true)
    @Nullable
    private Integer waist;
}
