package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.service.BookingRequestService;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.mappers.BookingRequestDTOMapper;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}
