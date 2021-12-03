package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.PersonalInfo;
import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.PersonalInfoDTO;
import com.baeldung.resource.web.mappers.PersonalInfoDTOMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/private/users")
public class UserManagementController {

    private UserService service;

    public UserManagementController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public Collection<PersonalInfoDTO> findAll() {
        Iterable<PersonalInfo> users = this.service.findAll();
        List<PersonalInfoDTO> dtos = new ArrayList<>();
        users.forEach(p -> dtos.add(PersonalInfoDTOMapper.convertToDto(p)));
        return dtos;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/user/{userId}")
    public void activateUser(@PathVariable Long userId) {
        log.info("Admin activated user id: {}", userId);
        this.service.activateUser(userId);
    }

}
