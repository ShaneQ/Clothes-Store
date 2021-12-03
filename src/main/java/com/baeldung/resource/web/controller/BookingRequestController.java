package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.service.BookingRequestService;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.baeldung.resource.web.mappers.BookingRequestDTOMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
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
public class BookingRequestController {

    private BookingRequestService service;

    public BookingRequestController(BookingRequestService bookingRequestService) {
        this.service = bookingRequestService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/public/booking")
    public void create(@RequestBody BookingRequestDTO dto, HttpServletRequest request) {

        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getPrincipal().getName();

        dto.setStatus(StatusDTO.WAITING_COLLECTION);

        BookingRequest savedEntity = this.service.save(dto, userId);

        log.info("Booking Created with id:{}", savedEntity.getId());
    }

    @GetMapping("/api/public/booking")
    public Collection<BookingRequestDTO> findAllForUser(HttpServletRequest request) {
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getPrincipal().getName();
        Iterable<BookingRequest> orders = this.service.findAllByUser(userId);
        List<BookingRequestDTO> dtos = new ArrayList<>();
        orders.forEach(p -> dtos.add(BookingRequestDTOMapper.convertToDto(p)));
        return dtos;
    }

    @GetMapping("/api/private/booking")
    public Collection<BookingRequestDTO> findAll() {
        Iterable<BookingRequest> orders = this.service.findAll();
        List<BookingRequestDTO> dtos = new ArrayList<>();
        orders.forEach(p -> dtos.add(BookingRequestDTOMapper.convertToDto(p)));
        return dtos;
    }

}
