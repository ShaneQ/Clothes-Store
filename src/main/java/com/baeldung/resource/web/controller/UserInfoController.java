package com.baeldung.resource.web.controller;

import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.KeycloakUserInfo;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {


    private UserService service;

    public UserInfoController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/registration/user/info")
    public KeycloakUserInfo getUserInfo(Principal principal) {

        String keycloakUserId = principal.getName();
        return service.getKeycloakInfo(keycloakUserId);
    }

}