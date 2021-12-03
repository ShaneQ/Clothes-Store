package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.PersonalInfo;
import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.PersonalInfoDTO;
import com.baeldung.resource.web.mappers.PersonalInfoDTOMapper;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/public/personal")
public class PersonalInfoController {

    private UserService service;

    public PersonalInfoController(UserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody PersonalInfoDTO dto, HttpServletRequest request) {

        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getPrincipal().getName();
        log.info("Personal info create for User: {}", userId);
        PersonalInfo savedEntity = this.service.create(dto, userId);

        log.info("Personal Info Created with id:{}", savedEntity.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody PersonalInfoDTO dto, HttpServletRequest request) {

        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getPrincipal().getName();

        PersonalInfo savedEntity = this.service.update(dto, userId);

        log.info("Personal Info Updated with id:{}", savedEntity.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public PersonalInfoDTO get(HttpServletRequest request) {
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getPrincipal().getName();
        log.info("Personal info request for User: {}", userId);

        PersonalInfo entity = this.service.get(userId);
        log.info("Personal Info Retrieved with id:{}", entity.getId());

        return PersonalInfoDTOMapper.convertToDto(entity);
    }
}
