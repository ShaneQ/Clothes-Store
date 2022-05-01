package com.baeldung.resource.persistence.repository;

import com.baeldung.resource.persistence.model.BookingRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IBookingRequestRepository extends PagingAndSortingRepository<BookingRequest, Long> {

    List<BookingRequest> findAllByUserIdAndStartDateGreaterThan(UUID userId, LocalDate startDate);
    @Query("SELECT a from BOOKING_REQUEST a  left join ProductSize b on a.productInventory.id=b.id where b.id_product = :productId")
    List<BookingRequest> findAllByProductId(@Param("productId") long productId);

}
