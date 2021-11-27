package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.BookingRequest.Status;
import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.baeldung.resource.web.dto.ImageDTO;

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
        return BookingRequestDTO.builder()
                .id(entity.getId())
                .productId(entity.getId_product().getId())
                .productSize(entity.getId_productSize())
                .startDate(entity.getStartDate())
                .status(StatusDTO.valueOf(entity.getStatus().name()))
                .coverImg(new ImageDTO(entity.getId_product().getImgCover().getId(), entity.getId_product().getImgCover().getPath()))
                .collectionPlace(entity.getCollectionPlace())
                .build();
    }
}
