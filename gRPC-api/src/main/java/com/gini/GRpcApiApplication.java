package com.gini;

import com.gini.config.Auth0ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(Auth0ConfigProperties.class)
@SpringBootApplication
public class GRpcApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GRpcApiApplication.class, args);
    }

}
