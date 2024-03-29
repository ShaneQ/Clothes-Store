package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.dto.UserManagementDTO;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin")
public class UserManagementController {

    private final UserService service;

    public UserManagementController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public Collection<UserDTO> findAll() {
        Iterable<User> users = this.service.findAll();
        List<UserDTO> dtos = new ArrayList<>();
        users.forEach(p -> dtos.add(UserDTOMapper.convertToDto(p)));
        return dtos;
    }

    @GetMapping("/user/{id}")
    public UserDTO find(@PathVariable UUID id){
        User user = this.service.get(id);
        return UserDTOMapper.convertToDto(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/users/user/{userId}")
    public UserDTO updateUserSettings(@PathVariable UUID userId, @RequestBody UserManagementDTO dto) {
        log.info("Admin updated user settings id: {}", userId);
        return UserDTOMapper.convertToDto(this.service.updateUserSettings(userId, dto));
    }

}
