package br.com.apigateway.proxi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SecurityGateway {

    @Bean
    WebClient securityWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:2734")
                .build();
    }

}
