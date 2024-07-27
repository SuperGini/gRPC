package com.gini.exceptions.handler;

import com.gini.exceptions.CarNotFoundException;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@GrpcAdvice
public class GrpcErrorHandler {

    @GrpcExceptionHandler(CarNotFoundException.class)
    public Status handleCarNotFoundException(CarNotFoundException ex) {
        log.error("Error: ", ex);
        return Status.NOT_FOUND
                .withDescription(ex.getMessage())
                .augmentDescription("bla bla bla ");

    }

    @GrpcExceptionHandler(Exception.class)
    public Status handleException(Exception ex) {
        log.error("Error: ", ex);
        return Status.INTERNAL.withDescription("shit happens!!!! :D");
    }

}
