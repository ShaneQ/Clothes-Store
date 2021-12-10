package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.KeycloakUserInfo;

import com.baeldung.resource.web.dto.UserDTO;
import java.security.Principal;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {


    private UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/info")
    public KeycloakUserInfo getUserInfo(Principal principal) {

        String keycloakUserId = principal.getName();
        return service.getKeycloakInfo(keycloakUserId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/personal")
    public void create(@RequestBody UserDTO dto, Principal principal) {

        String userId = principal.getName();
        log.info("Personal info create for User: {}", userId);
        User savedEntity = this.service.create(dto, userId);

        log.info("Personal Info Created with id:{}", savedEntity.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/personal/exists")
    public Response exists(Principal principal) {

        String userId = principal.getName();
        log.info("Check user registered id: {}", userId);
        User entity = this.service.get(userId);
        log.info("User with id exists : {}", entity.getId());
        return Response.ok().build();
    }

}