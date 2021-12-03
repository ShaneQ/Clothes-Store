package com.baeldung.resource.persistence.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ProductMeasurement implements Serializable {

    @Id
    @Column(name = "id_product")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_product")
    private Product product;
    private String length;
    private String chest;
    private String hips;
    private String waist;
}
