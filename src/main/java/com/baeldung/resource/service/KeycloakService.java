package com.baeldung.resource.service;

import com.baeldung.resource.spring.properties.KeycloakClientProperties;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Component
@Slf4j
public class KeycloakService {

    private final RestTemplate restTemplate;

    private final Keycloak keycloakClient;

    private KeycloakClientProperties keycloakClientProperties;

    private static final String roleMappingURL = "/auth/admin/realms/{realm}/users/{user}/role-mappings/clients/{clientId}";



    public KeycloakService(RestTemplateBuilder builder, Keycloak keycloakClient,
            KeycloakClientProperties keycloakClientProperties) {
        this.restTemplate = builder.build();
        this.keycloakClient = keycloakClient;
        this.keycloakClientProperties = keycloakClientProperties;
    }

    public void addSCCUserRole(String userId){

        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setId(keycloakClientProperties.getRoleId());
        roleRepresentation.setName("scc_user_role");
        roleRepresentation.setComposite(false);
        roleRepresentation.setClientRole(true);
        roleRepresentation.setContainerId(keycloakClientProperties.getClientId());
        keycloakClient.realm("secondClosetClub").users().get(userId).roles().clientLevel(keycloakClientProperties.getClientId()).add(List.of(roleRepresentation));;
    }

    public Keycloak keycloakClient(){
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

    public AccessTokenResponse newKeycloakLogin(){
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
}
