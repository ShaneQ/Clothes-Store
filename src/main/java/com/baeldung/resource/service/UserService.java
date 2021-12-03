package com.baeldung.resource.service;

import com.baeldung.resource.exceptions.ResourceNotFound;
import com.baeldung.resource.persistence.model.PersonalInfo;
import com.baeldung.resource.persistence.repository.IPersonalInfoRepository;
import com.baeldung.resource.web.dto.PersonalInfoDTO;
import com.baeldung.resource.web.mappers.PersonalInfoDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private IPersonalInfoRepository repository;

    private KeycloakService keycloakService;

    public UserService(IPersonalInfoRepository repository,
            KeycloakService keycloakService) {
        this.repository = repository;
        this.keycloakService = keycloakService;
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

    public PersonalInfo get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFound("No Personal Info found"));
    }

    public Iterable<PersonalInfo> findAll() {
        return repository.findAll();
    }

    public void activateUser(Long id) {
        PersonalInfo personalInfo = this.get(id);
        keycloakService.addSCCUserRole(personalInfo.getUserId());

    }
}
