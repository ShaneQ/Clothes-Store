package com.baeldung.resource.web.dto;

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

    private String userId;

    private ImageDTO coverImg;

    private Long productSize;

    private Date startDate;

    private String collectionPlace;

    private StatusDTO status;

    public enum StatusDTO {
        WAITING_COLLECTION,
        ACTIVE,
        LATE_RETURN,
        COMPLETE;

    }
}
