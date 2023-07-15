package com.example.test.canvas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class CanvasOAuth2ProviderConfiguration {

    private final OAuth2ClientProperties oauth2ClientProperties;

    @Autowired
    public CanvasOAuth2ProviderConfiguration(OAuth2ClientProperties oauth2ClientProperties) {
        this.oauth2ClientProperties = oauth2ClientProperties;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = oauth2ClientProperties.getRegistration()
                .values().stream()
                .map(this::configureCanvasProvider)
                .collect(Collectors.toList());
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration configureCanvasProvider(OAuth2ClientProperties.Registration registration) {
        OAuth2ClientProperties.Provider provider = oauth2ClientProperties.getProvider().get("canvas"); // Replace with your registration ID
        return ClientRegistration.withRegistrationId("canvas") // Replace with your registration ID
                .clientId(registration.getClientId())
                .clientSecret(registration.getClientSecret())
                .clientName(registration.getClientName())
                .redirectUri(registration.getRedirectUri())
                .authorizationGrantType(new AuthorizationGrantType(registration.getAuthorizationGrantType()))
                .authorizationUri(provider.getAuthorizationUri())
                .tokenUri(provider.getTokenUri())
                .build();
    }

}
