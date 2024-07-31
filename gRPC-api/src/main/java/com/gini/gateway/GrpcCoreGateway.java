package com.gini.gateway;


import com.gini.request.CarResponse;
import com.gini.services.CarServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcCoreGateway {



    private final CarServiceGrpc.CarServiceBlockingStub carServiceStub;
    private final OAuth2AuthorizedClientManager authorizedClientManager;


    public CarResponse getCar() {
        return null;
    }



}
