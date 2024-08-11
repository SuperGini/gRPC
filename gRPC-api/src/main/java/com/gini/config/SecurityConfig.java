package com.gini.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.ClientCredentialsOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.endpoint.DefaultClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.LinkedMultiValueMap;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final Auth0ConfigProperties aut0Properties;
    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, JwtDecoder decoder) throws Exception {

        corsConfig.corsConfiguration(http);
        http.authorizeHttpRequests(x ->
                x.requestMatchers("/car/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()

        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> jwt
                        .decoder(decoder) //checks for the token signature
                        .jwtAuthenticationConverter(new JwtConverter()) //ads the authorities to the authentication token
                )
        );

        return http
                .build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder =
                JwtDecoders.fromOidcIssuerLocation("https://dev-u6p6egz1bn2h0sye.eu.auth0.com/");

//        var withIssuer = JwtValidators.createDefaultWithIssuer(authServerUrl);
//        var withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, new AudienceValidator());

//        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;

    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration c1 = ClientRegistration.withRegistrationId(aut0Properties.clientRegistrationId())
                .clientId(aut0Properties.clientId())
                .clientSecret(aut0Properties.clientSecret())
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenUri(aut0Properties.tokenUri())
                .build();

        return new InMemoryClientRegistrationRepository(c1);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            OAuth2AuthorizedClientProvider authorizedClientProvider) {

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    /**
     * add the audience on the token request
     * <a href="https://spring.io/blog/2024/03/19/token-exchange-support-in-spring-security-6-3-0-m3">RequestToken with audience</a>
     */

    @Bean
    public OAuth2AuthorizedClientProvider clientCredentialsProvider() {

        var requestEntityConverter = new OAuth2ClientCredentialsGrantRequestEntityConverter();
        requestEntityConverter.addParametersConverter(grantRequest -> {
            var parameters = new LinkedMultiValueMap<String, String>();
            parameters.add(OAuth2ParameterNames.AUDIENCE, aut0Properties.audience());

            return parameters;
        });

        var accessTokenResponseClient = new DefaultClientCredentialsTokenResponseClient();
        accessTokenResponseClient.setRequestEntityConverter(requestEntityConverter);

        var authorizedClientProvider = new ClientCredentialsOAuth2AuthorizedClientProvider();
        authorizedClientProvider.setAccessTokenResponseClient(accessTokenResponseClient);

        return authorizedClientProvider;
    }

}
