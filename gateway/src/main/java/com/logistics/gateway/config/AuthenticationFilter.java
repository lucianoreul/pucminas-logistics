package com.logistics.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {


    private final RouterValidator routerValidator;

    private final WebClient.Builder webClientBuilder;

    private static final String TOKEN_PREFIX = "Bearer";

    private final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    public AuthenticationFilter(RouterValidator routerValidator, WebClient.Builder webClientBuilder) {
        this.routerValidator = routerValidator;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            }

            final String token = this.getAuthHeader(request);

            return webClientBuilder.build()
                    .post()
                    .uri("http://AUTH/api/auth/validate?token=" + token)
                    .retrieve().bodyToMono(AccountVO.class)
                    .map(accountVO -> {
                        log.debug(accountVO.getUsername());
                        exchange.getRequest()
                                .mutate()
                                .header("X-auth-user-id", String.valueOf(accountVO.getId()));
                        return exchange;
                    }).flatMap(chain::filter);
        }
        return chain.filter(exchange);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).replace(TOKEN_PREFIX, "").trim();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
