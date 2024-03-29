package com.baeldung.resource.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public/health")
@AllArgsConstructor
public class HealthController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public void isUp() {
    }
}
