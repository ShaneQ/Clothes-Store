package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.BookingRequest.Status;
import com.baeldung.resource.persistence.model.ProductInventory;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.baeldung.resource.web.dto.ImageDTO;
import com.baeldung.resource.web.dto.ProductSizeDTO;
import java.util.Calendar;
import java.util.Date;

public class BookingRequestDTOMapper {

    public static BookingRequest convertToEntity(BookingRequestDTO dto) {
        return BookingRequest.builder()
                .collectionPlace(dto.getCollectionPlace())
                .productInventory(ProductInventory.builder().id(dto.getProductSize().getId()).build())
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
                .productId(entity.getProductInventory().getId_product().getId())
                .productSize(ProductSizeDTO.builder().id(entity.getProductInventory().getSize().getId())
                        .id_size(entity.getProductInventory().getSize().getId()).build())
                .startDate(entity.getStartDate())
                .returnDate(returnDate)
                .userId(entity.getUser().getId().toString())
                .userName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName())
                .status(StatusDTO.valueOf(entity.getStatus().name()))
                .productName(entity.getProductInventory().getId_product().getName())
                .coverImg(new ImageDTO(entity.getProductInventory().getId_product().getImgCover().getId(),
                        entity.getProductInventory().getId_product().getImgCover().getPath()))
                .collectionPlace(entity.getCollectionPlace())
                .build();
    }
}
