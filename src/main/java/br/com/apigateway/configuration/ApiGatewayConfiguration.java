package br.com.apigateway.configuration;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class ApiGatewayConfiguration implements GlobalFilter, Ordered {

    private final WebClient webClient;

    public ApiGatewayConfiguration(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String key = "gateway";
        String target = String.valueOf(exchange.getRequest().getPath().subPath(1,2));
        return webClient
                .get()
                .uri("/token?key=" + key+ "&target=" + target)
//                .attribute("key",key)
//                .attribute("key",target)
                .exchangeToMono(clientResponse -> {
                    String signature = clientResponse
                            .headers()
                            .asHttpHeaders()
                            .getFirst("X-Signature");

                    return clientResponse.bodyToMono(String.class)
                            .map(token -> new TokenDTO(token, signature));
                }).flatMap(token -> {
                    ServerHttpRequest request = exchange
                            .getRequest()
                            .mutate()
                            .header("Authorization-security", token.token())
                            .header("X-Signature", token.assinatura())
                            .build();

                    return chain.filter(exchange
                            .mutate()
                            .request(request)
                            .build());
                });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
