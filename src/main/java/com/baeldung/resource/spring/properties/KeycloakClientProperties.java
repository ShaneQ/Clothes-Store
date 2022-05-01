package com.baeldung.resource.spring.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "keycloak-client")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakClientProperties {

    private String serverUrl;
    private String username;
    private String password;
    private String clientId;
    private String roleUserId;
    private String roleActiveMemberId;
}