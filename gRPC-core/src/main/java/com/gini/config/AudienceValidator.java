package com.gini.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private static final String AUDIENCE = "gRPC-core-aud";

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        if (jwt.getAudience().contains(AUDIENCE))
            return OAuth2TokenValidatorResult.success();

        //log error here because it will not be sent to the error handler. I suspect that the exception is thrown in the
        // interceptor and the @GrpcAdvice is not catching exception thrown from the interceptor
        var error = new BadCredentialsException("An error occurred while attempting to decode the Jwt: invalid audience");
        log.error("Error: ", error);
        throw error;
    }
}
