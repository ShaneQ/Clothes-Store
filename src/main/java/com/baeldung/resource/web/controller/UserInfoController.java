package com.baeldung.resource.web.controller;

import com.baeldung.resource.service.UserService;
import com.baeldung.resource.web.dto.KeycloakUserInfo;

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

    @GetMapping("/api/public/user/info")
    public KeycloakUserInfo getUserInfo(HttpServletRequest request) {

        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String keycloakUserId = principal.getAccount().getPrincipal().getName();
        return service.getKeycloakInfo(keycloakUserId);
    }

}