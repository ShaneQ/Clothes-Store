package com.baeldung.resource.persistence.repository;

import com.baeldung.resource.persistence.model.BookingRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBookingRequestRepository extends PagingAndSortingRepository<BookingRequest, Long> {

    List<BookingRequest> findAllByUserId(UUID userId);
}
