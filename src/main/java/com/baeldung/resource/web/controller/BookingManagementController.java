package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.BookingRequest.Status;
import com.baeldung.resource.service.BookingRequestService;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.mappers.BookingRequestDTOMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin")
@RestController
@AllArgsConstructor
public class BookingManagementController {

    private final BookingRequestService service;

    @GetMapping("/bookings")
    public Collection<BookingRequestDTO> findAll() {
        Iterable<BookingRequest> entities = this.service.findAll();
        List<BookingRequestDTO> dtos = new ArrayList<>();
        entities.forEach(p -> dtos.add(BookingRequestDTOMapper.convertToDto(p)));
        return dtos;
    }

    @GetMapping("/bookings/{userGuid}")
    public Collection<BookingRequestDTO> findAllAdminForUser(@PathVariable UUID userGuid) {
        Iterable<BookingRequest> orders = this.service.findAllByUser(userGuid);
        List<BookingRequestDTO> dtos = new ArrayList<>();
        orders.forEach(p -> dtos.add(BookingRequestDTOMapper.convertToDto(p)));
        return dtos;
    }

    @PostMapping("/booking/{id}/status/{status}")
    public void updateBookingStatus(@PathVariable Long id, @PathVariable Status status) {
        this.service.updateStatus(id, status);
    }
}
