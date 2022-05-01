package com.baeldung.resource.service;

import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.persistence.model.BookingRequest.Status;
import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.persistence.repository.IBookingRequestRepository;
import com.baeldung.resource.web.dto.BookingRequestDTO;
import com.baeldung.resource.web.mappers.BookingRequestDTOMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class BookingRequestService {

    private final IBookingRequestRepository repository;

    public BookingRequestService(IBookingRequestRepository repository) {
        this.repository = repository;
    }

    public BookingRequest save(BookingRequestDTO dto, UUID userId) {
        BookingRequest entity = BookingRequestDTOMapper.convertToEntity(dto);
        entity.setUser(User.builder().id(userId).build());
        return repository.save(entity);
    }

    public List<BookingRequest> findAllByUser(UUID userId, LocalDate fromDate) {
        return repository.findAllByUserIdAndStartDateGreaterThan(userId, fromDate);
    }

    public Iterable<BookingRequest> findAllByProduct(Long productId) {
        return repository.findAllByProductId(productId);
    }

    public Iterable<BookingRequest> findAll() {
        return repository.findAll();
    }

    public BookingRequest get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void updateStatus(Long id, Status status) {
        BookingRequest bookingRequest = get(id);
        bookingRequest.setStatus(status);
        log.info("Booking {} updated with status {} for user {}", id, status, bookingRequest.getUser().getId());
        repository.save(bookingRequest);
    }
}
