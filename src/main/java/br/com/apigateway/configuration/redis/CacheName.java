package br.com.apigateway.configuration.redis;

import java.time.Duration;
import java.util.List;

public class CacheName {

    public static final String token = "token";

    static List<RedisDTO> cache() {
        return List.of(
                new RedisDTO(token, Duration.ofMinutes(30))
        );
    }

}
