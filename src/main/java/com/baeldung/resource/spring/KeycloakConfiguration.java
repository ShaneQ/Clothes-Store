package com.baeldung.resource.spring;

import com.baeldung.resource.spring.properties.KeycloakClientProperties;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {

    private KeycloakClientProperties properties;

    public KeycloakConfiguration(KeycloakClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Keycloak keycloakClient(){
        return  KeycloakBuilder.builder()
                .serverUrl(properties.getServerUrl())
                .grantType(OAuth2Constants.PASSWORD)
                .realm("master")
                .clientId("admin-cli")
                .username(properties.getUsername())
                .password(properties.getPassword())
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                ).build();

    }

}
