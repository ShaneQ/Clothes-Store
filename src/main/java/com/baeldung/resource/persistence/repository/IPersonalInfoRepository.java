package com.baeldung.resource.persistence.repository;

import com.baeldung.resource.persistence.model.PersonalInfo;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPersonalInfoRepository extends PagingAndSortingRepository<PersonalInfo, Long> {

    Optional<PersonalInfo> findByUserId(String userId);
}
