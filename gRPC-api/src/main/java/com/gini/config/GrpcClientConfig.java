package com.gini.config;

import com.gini.services.CarServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.inject.GrpcClientBean;
import org.springframework.context.annotation.Configuration;

@GrpcClientBean(
        clazz = CarServiceGrpc.CarServiceBlockingStub.class,
        beanName = "carServiceStub", //name of the bean in case we need to use @Qualifier
        client = @GrpcClient("gRPC-core") //name of the service that we call -> see application.yaml file
)
@Configuration
public class GrpcClientConfig {


}
