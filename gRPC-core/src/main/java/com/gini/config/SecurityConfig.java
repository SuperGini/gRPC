package com.gini.config;

import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig {

    private final String authServerUrl;

    public SecurityConfig(@Value("${auth-server.url}") String authServerUrl) {
        this.authServerUrl = authServerUrl;
    }

    @Bean
    AuthenticationManager authenticationManager(JwtDecoder jwtDecoder) {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new JwtAuthenticationProvider(jwtDecoder)); // Possibly JwtAuthenticationProvider
        return new ProviderManager(providers);
    }

    @Bean
    GrpcAuthenticationReader authenticationReader() {
        final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        // The actual token class is dependent on your spring-security library (OAuth2/JWT/...)
        readers.add(new BearerAuthenticationReader(BearerTokenAuthenticationToken::new));
        return new CompositeGrpcAuthenticationReader(readers);
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder =
                JwtDecoders.fromOidcIssuerLocation(authServerUrl);

        var withIssuer = JwtValidators.createDefaultWithIssuer(authServerUrl);
        var withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, new AudienceValidator());

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;

    }


}
