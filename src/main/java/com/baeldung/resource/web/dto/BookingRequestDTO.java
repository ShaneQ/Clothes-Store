package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.ProductInventoryStatus;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    private Long id;

    private Long productId;

    private String productName;

    private String userId;

    private String userName;

    private ImageDTO coverImg;

    private ProductSizeDTO productSize;

    private LocalDate startDate;

    private LocalDate returnDate;

    private String collectionPlace;

    private StatusDTO status;

    public enum StatusDTO {
        WAITING_COLLECTION,
        ACTIVE,
        LATE_RETURN,
        COMPLETE;
    }
}
