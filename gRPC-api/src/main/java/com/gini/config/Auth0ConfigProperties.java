package com.gini.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
//@EnableConfigurationProperties(Auth0ConfigProperties.class) -> add this on main class so configuration properties work with records
@ConfigurationProperties(prefix = "auth0")
public record Auth0ConfigProperties(
        String clientId,
        String clientSecret,
        String tokenUri,
        String audience,
        String clientRegistrationId
) {
}
