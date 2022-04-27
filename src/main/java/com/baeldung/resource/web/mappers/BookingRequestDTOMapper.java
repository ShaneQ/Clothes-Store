package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.BookingRequest.Status;
import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.baeldung.resource.web.dto.ImageDTO;
import java.util.Calendar;
import java.util.Date;

public class BookingRequestDTOMapper {

    public static BookingRequest convertToEntity(BookingRequestDTO dto) {
        return BookingRequest.builder()
                .collectionPlace(dto.getCollectionPlace())
                .id_product(Product.builder().id(dto.getProductId()).build())
                .id_productSize(dto.getProductSize())
                .startDate(dto.getStartDate())
                .status(Status.valueOf(dto.getStatus().name())).build();
    }

    public static BookingRequestDTO convertToDto(BookingRequest entity) {
        Date returnDate = entity.getStartDate();
        Calendar c = Calendar.getInstance();
        c.setTime(returnDate);
        c.add(Calendar.DATE, 7);
        returnDate = c.getTime();
        return BookingRequestDTO.builder()
                .id(entity.getId())
                .productId(entity.getId_product().getId())
                .productSize(entity.getId_productSize())
                .startDate(entity.getStartDate())
                .returnDate(returnDate)
                .userId(entity.getUser().getId().toString())
                .userName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName())
                .status(StatusDTO.valueOf(entity.getStatus().name()))
                .productName(entity.getId_product().getName())
                .coverImg(new ImageDTO(entity.getId_product().getImgCover().getId(),
                        entity.getId_product().getImgCover().getPath()))
                .collectionPlace(entity.getCollectionPlace())
                .build();
    }
}
