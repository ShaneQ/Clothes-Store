package com.baeldung.resource.service;

import com.baeldung.resource.exceptions.ResourceNotFound;
import com.baeldung.resource.persistence.model.PersonalInfo;
import com.baeldung.resource.persistence.repository.IPersonalInfoRepository;
import com.baeldung.resource.web.dto.PersonalInfoDTO;
import com.baeldung.resource.web.mappers.PersonalInfoDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoService {

    private IPersonalInfoRepository repository;

    public PersonalInfoService(IPersonalInfoRepository repository) {
        this.repository = repository;
    }

    public PersonalInfo create(PersonalInfoDTO dto, String userId) {
        PersonalInfo entity = PersonalInfoDTOMapper.convertToEntity(dto, userId);
        return repository.save(entity);
    }
    public PersonalInfo update(PersonalInfoDTO dto, String userId) {
        PersonalInfo entity = PersonalInfoDTOMapper.convertToEntity(dto, userId);
        return repository.save(entity);
    }

    public PersonalInfo get(String userId) {
        return repository.findByUserId(userId).orElseThrow(() -> new ResourceNotFound("No Personal Info found"));
    }
}
