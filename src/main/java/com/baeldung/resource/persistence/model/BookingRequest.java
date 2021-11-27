package com.baeldung.resource.persistence.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BOOKING_REQUEST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_product")
    private Product id_product;

    @Column(name = "id_user")
    private String userId;

    private Long id_productSize;

    private Date startDate;

    private String collectionPlace;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        WAITING_COLLECTION,
        ACTIVE,
        WAITING_RETURN,
        COMPLETE;
    }
}


