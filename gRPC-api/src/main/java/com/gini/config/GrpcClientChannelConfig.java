package com.gini.config;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class GrpcClientChannelConfig {

    //we can add here interceptors
    @Bean  //https://grpc.io/docs/guides/keepalive/
    public GrpcChannelConfigurer keepAliveClientConfigurer() {
        return (channelBuilder, name) ->
                channelBuilder
                        .keepAliveTime(30, TimeUnit.SECONDS) //used for streaming, does not have a big impact on unary -> how often to send the ping
                        .keepAliveTimeout(5, TimeUnit.SECONDS); //used for streaming, does not have a big impact on unary -> timeout for the ping response

    }

}
