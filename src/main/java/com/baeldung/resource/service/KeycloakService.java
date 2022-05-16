package com.baeldung.resource.service;

import com.baeldung.resource.spring.properties.KeycloakClientProperties;
import com.baeldung.resource.web.dto.KeycloakUserInfo;
import com.baeldung.resource.web.dto.Role;
import com.baeldung.resource.web.mappers.KeycloakDTOMapper;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KeycloakService {

    private static final String SECOND_CLOSET_CLUB = "secondClosetClub";
    private final Keycloak keycloakClient;
    private KeycloakClientProperties keycloakClientProperties;

    public KeycloakService(Keycloak keycloakClient,
            KeycloakClientProperties keycloakClientProperties) {
        this.keycloakClient = keycloakClient;
        this.keycloakClientProperties = keycloakClientProperties;
    }

    public void addRole(UUID userId, Role role) {
        String roleName = role.name().toLowerCase(Locale.ROOT);

        boolean hasRole = keycloakClient.realm(SECOND_CLOSET_CLUB).users().get(userId.toString()).roles()
                .clientLevel(keycloakClientProperties.getClientId()).listAll().stream()
                .anyMatch(roleRep -> roleRep.getName().equalsIgnoreCase(roleName));
        if (!hasRole) {
            log.info("Added role {} to user {}", roleName, userId);
            keycloakClient.realm(SECOND_CLOSET_CLUB).users().get(userId.toString()).roles()
                    .clientLevel(keycloakClientProperties.getClientId()).add(List.of(getKeycloakRole(role)));
        }
    }

    private RoleRepresentation getKeycloakRole(Role role) {
        String roleName = role.name().toLowerCase(Locale.ROOT);
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        switch (role) {
            case SCC_ACTIVE_MEMBERSHIP:
                roleRepresentation.setId(keycloakClientProperties.getRoleActiveMemberId());
                break;
            case SCC_USER_ROLE:
                roleRepresentation.setId(keycloakClientProperties.getRoleUserId());
                break;
        }
        roleRepresentation.setName(roleName);
        roleRepresentation.setComposite(false);
        roleRepresentation.setClientRole(true);
        roleRepresentation.setContainerId(keycloakClientProperties.getClientId());
        return roleRepresentation;
    }

    public void removeRole(UUID userId, Role role) {
        String roleName = role.name().toLowerCase(Locale.ROOT);
        boolean hasRole = keycloakClient.realm(SECOND_CLOSET_CLUB).users().get(userId.toString()).roles()
                .clientLevel(keycloakClientProperties.getClientId()).listAll().stream()
                .anyMatch(roleRep -> roleRep.getName().equalsIgnoreCase(roleName));
        if (hasRole) {

            log.info("Removed role {} to user {}", roleName, userId);
            keycloakClient.realm(SECOND_CLOSET_CLUB).users().get(userId.toString()).roles()
                    .clientLevel(keycloakClientProperties.getClientId()).remove(List.of(getKeycloakRole(role)));
        }
    }

    public Keycloak keycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8083/auth")
                .grantType(OAuth2Constants.PASSWORD)
                .realm("master")
                .clientId("admin-cli")
                .username("masterapiuser")
                .password("masterapiuser")
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                ).build();
    }

    public AccessTokenResponse newKeycloakLogin() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8083/auth")
                .grantType(OAuth2Constants.PASSWORD)
                .realm("master")
                .clientId("admin-cli")
                .username("masterapiuser")
                .password("masterapiuser")
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                ).build();

        return keycloak.tokenManager().getAccessToken();
    }

    public KeycloakUserInfo getUserInfo(UUID userId) {
        UserRepresentation userRepresentation = keycloakClient.realm(SECOND_CLOSET_CLUB).users().get(userId.toString())
                .toRepresentation();

        return KeycloakDTOMapper.convertToDto(userRepresentation);
    }
}
