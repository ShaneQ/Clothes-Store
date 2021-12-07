package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import java.security.Principal;
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
@RequestMapping(value = "/api")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration/personal")
    public void create(@RequestBody UserDTO dto, Principal principal) {

        String userId = principal.getName();
        log.info("Personal info create for User: {}", userId);
        User savedEntity = this.service.create(dto, userId);

        log.info("Personal Info Created with id:{}", savedEntity.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/private/personal/{id}")
    public void update(@RequestBody UserDTO dto, Principal principal) {

        String userId = principal.getName();

        User savedEntity = this.service.update(dto, userId);

        log.info("Personal Info Updated with id:{}", savedEntity.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/private/personal")
    public UserDTO get( Principal principal) {

        String keycloakUserId = principal.getName();
        log.info("Personal info request for User: {}", keycloakUserId);

        User entity = this.service.get(keycloakUserId);
        log.info("Personal Info Retrieved with id:{}", entity.getId());

        return UserDTOMapper.convertToDto(entity);
    }
}
