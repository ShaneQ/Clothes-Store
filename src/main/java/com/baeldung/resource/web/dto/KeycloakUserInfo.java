package com.baeldung.resource.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserInfo {

    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
