package com.baeldung.resource.persistence.repository;

import com.baeldung.resource.persistence.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findById(UUID fromString);
}
