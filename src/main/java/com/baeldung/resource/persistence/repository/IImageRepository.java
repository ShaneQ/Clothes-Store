package com.baeldung.resource.persistence.repository;

import com.baeldung.resource.persistence.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IImageRepository extends PagingAndSortingRepository<Image, Long> {

}
