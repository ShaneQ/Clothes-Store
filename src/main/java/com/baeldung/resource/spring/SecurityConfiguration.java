package com.baeldung.resource.spring;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        final KeycloakAuthenticationProvider authProvider = keycloakAuthenticationProvider();

        authProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        authBuilder.authenticationProvider(authProvider);
    }

    /**
     * Call superclass configure method and set the Keycloak configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable()
                .cors().and().authorizeRequests().antMatchers("/actuator/**", "/api/**").permitAll();

/*        http.cors().and().authorizeRequests()
                .antMatchers("/api/public*").permitAll()
                .antMatchers("/api/private*").hasRole("scc_admin_role")
                .anyRequest()
                .permitAll()
                .and().csrf().disable();*/
    }

    /**
     * Setup Auth Strategy. Don't add prefixes and suffixes to role strings
     */
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    /**
     * Don't use keycloak.json. Instead, use application.yml properties.
     */
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
