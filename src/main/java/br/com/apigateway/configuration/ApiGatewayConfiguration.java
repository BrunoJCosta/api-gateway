package br.com.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("hello", "word")
                                .addRequestParameter("hello", "word"))
                        .uri("http://httpbin.org:80"))
//                .route(p -> p
//                        .path("/login/**")
//                        .uri("lb://login")
//                )
                .route(p -> p
                        .path("/cambio/**")
                        .uri("lb://cambio-service"))
                .route(p -> p
                        .path("/book/**")
                        .uri("lb://book-service"))
                .route(p -> p
                        .path("/estoque/**")
                        .uri("lb://estoque-server"))
                .build();
    }
}
