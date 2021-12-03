package com.baeldung.resource.service;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.repository.IBookingRequestRepository;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.mappers.BookingRequestDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class BookingRequestService {

    private IBookingRequestRepository repository;

    public BookingRequestService(IBookingRequestRepository repository) {
        this.repository = repository;
    }

    public BookingRequest save(BookingRequestDTO dto, String userId) {
        BookingRequest entity = BookingRequestDTOMapper.convertToEntity(dto);
        entity.setUserId(userId);
        return repository.save(entity);
    }

    public Iterable<BookingRequest> findAllByUser(String userId) {
        return repository.findAllByUserId(userId);
    }

    public Iterable<BookingRequest> findAll() {
        return repository.findAll();
    }
}
