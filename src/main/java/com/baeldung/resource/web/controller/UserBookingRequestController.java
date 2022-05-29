package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.service.BookingRequestService;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.baeldung.resource.web.mappers.BookingRequestDTOMapper;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping()
public class UserBookingRequestController {

    private final BookingRequestService service;

    public UserBookingRequestController(BookingRequestService bookingRequestService) {
        this.service = bookingRequestService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/private/booking")
    public void create(@RequestBody BookingRequestDTO dto, Principal principal) {

        UUID userGuid = UUID.fromString(principal.getName());

        dto.setStatus(StatusDTO.WAITING_COLLECTION);

        BookingRequest savedEntity = this.service.save(dto, userGuid);

        log.info("Booking created with id:{}", savedEntity.getId());
    }

    @GetMapping("/api/private/bookings")
    public Collection<BookingRequestDTO> findAllForUser(Principal principal) {

        UUID userGuid = UUID.fromString(principal.getName());
        Iterable<BookingRequest> orders = this.service.findAllByUser(userGuid, LocalDate.now().minusYears(1));
        List<BookingRequestDTO> dtos = new ArrayList<>();
        orders.forEach(p -> dtos.add(BookingRequestDTOMapper.convertToDto(p)));
        return dtos;
    }
}
