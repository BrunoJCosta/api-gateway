package br.com.apigateway.configuration.redis;

import java.time.Duration;

public record RedisDTO(String name, Duration duration) {
}
