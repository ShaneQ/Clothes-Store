package com.baeldung.resource.web.mappers;

import com.baeldung.resource.web.dto.KeycloakUserInfo;
import org.keycloak.representations.idm.UserRepresentation;

public class KeycloakDTOMapper {

    public static KeycloakUserInfo convertToDto(UserRepresentation entity) {
        return KeycloakUserInfo.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .build();
    }
}
