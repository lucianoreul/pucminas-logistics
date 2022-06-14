package com.logistics.gateway.config;

import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.List;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = Arrays.asList(
            "/api/auth/register",
            "/api/auth/login"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
