package com.baeldung.resource.service;


import static org.junit.Assert.assertNotNull;

import com.baeldung.resource.spring.properties.KeycloakClientProperties;
import java.util.UUID;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
@SpringBootTest
public class KeycloakServiceTest {

    private KeycloakService keycloakService;

    @Before
    public void setUp() {

        KeycloakClientProperties keycloakClientProperties = KeycloakClientProperties.builder()
                .serverUrl("http://localhost:8083/auth")
                .clientId("276928b9-4218-4443-bec7-956b20a610b0")
                .password("masterapiuser")
                .username("masterapiuser")
                .roleId("23aa99f0-6e9d-46fa-8bbd-51902da41834")
                .build();
        Keycloak keycloakClient = KeycloakBuilder.builder()
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
        keycloakService= new KeycloakService(new RestTemplateBuilder(), keycloakClient,
                keycloakClientProperties);
    }

    @Test
    @Ignore
    public void loginNewTest(){
        AccessTokenResponse accessTokenResponse = keycloakService.newKeycloakLogin();
        assertNotNull(accessTokenResponse.getToken());
        assertNotNull(accessTokenResponse.getRefreshToken());

    }

    @Test
    @Ignore
    public void mapRoleToUserClient(){
        keycloakService.addSCCUserRole(UUID.fromString("bfa1f3f3-3bac-4968-b6fd-d97b41976c26"));

    }
}