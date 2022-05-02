package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.BookingRequest.Status;
import com.baeldung.resource.persistence.model.Product;
import com.baeldung.resource.persistence.model.ProductInventory;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.baeldung.resource.web.dto.ImageDTO;
import com.baeldung.resource.web.dto.ProductSizeDTO;
import java.time.LocalDate;

public class BookingRequestDTOMapper {

    public static BookingRequest convertToEntity(BookingRequestDTO dto, Product product) {
        return BookingRequest.builder()
                .collectionPlace(dto.getCollectionPlace())
                .productInventory(ProductInventory.builder().id(dto.getProductSize().getId()).build())
                .product(product)
                .startDate(dto.getStartDate())
                .status(Status.valueOf(dto.getStatus().name())).build();
    }

    public static BookingRequestDTO convertToDto(BookingRequest entity) {
        LocalDate returnDate = entity.getStartDate().plusDays(7);
        return BookingRequestDTO.builder()
                .id(entity.getId())
                .productId(entity.getProductInventory().getId_product())
                .productSize(ProductSizeDTO.builder().id(entity.getProductInventory().getSize().getId())
                        .id_size(entity.getProductInventory().getSize().getId()).build())
                .startDate(entity.getStartDate())
                .returnDate(returnDate)
                .userId(entity.getUser().getId().toString())
                .userName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName())
                .status(StatusDTO.valueOf(entity.getStatus().name()))
                .productName(entity.getProduct().getName())
                .coverImg(new ImageDTO(entity.getProduct().getImgCover().getId(),
                        entity.getProduct().getImgCover().getPath()))
                .collectionPlace(entity.getCollectionPlace())
                .build();
    }
}
