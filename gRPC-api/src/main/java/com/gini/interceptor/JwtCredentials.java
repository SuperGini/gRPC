package com.gini.interceptor;

import com.gini.config.Auth0ConfigProperties;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@RequiredArgsConstructor
public class JwtCredentials extends CallCredentials {

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final Auth0ConfigProperties auth0ConfigProperties;

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo,
                                     Executor appExecutor, //if we want to do the call async
                                     MetadataApplier applier) {

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(auth0ConfigProperties.clientRegistrationId())
                .principal("just put something here so it will not be null")
                .build();

        var token = authorizedClientManager.authorize(authorizeRequest)
                .getAccessToken()
                .getTokenValue();

       var key =  Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

        var metadata = new Metadata();
        metadata.put(key, "Bearer " + token);
        applier.apply(metadata);

    }
}
