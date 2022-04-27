package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import java.security.Principal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/private/personal/")
    public void update(@RequestBody UserDTO dto, Principal principal) {

        UUID userId = UUID.fromString(principal.getName());

        User savedEntity = this.service.update(dto, userId);

        log.info("Personal Info Updated with id:{}", savedEntity.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/private/personal")
    public UserDTO get(Principal principal) {

        UUID keycloakUserId = UUID.fromString(principal.getName());
        log.info("Personal info request for User: {}", keycloakUserId);

        User entity = this.service.get(keycloakUserId);
        log.info("Personal Info Retrieved with id:{}", entity.getId());

        return UserDTOMapper.convertToDto(entity);
    }
}
