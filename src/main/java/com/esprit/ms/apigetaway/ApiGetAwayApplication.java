package com.esprit.ms.apigetaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGetAwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetAwayApplication.class, args);
    }

    @Bean
    public RouteLocator getwayRoutes (RouteLocatorBuilder builder) {
        return builder.routes()
                .route("MScandidat",r->r.path("/candidats/**").uri("lb://MScandidat"))
                .route("Job",r->r.path("/job/jobs/**").uri("lb://Job"))
                .build();
    }
}
