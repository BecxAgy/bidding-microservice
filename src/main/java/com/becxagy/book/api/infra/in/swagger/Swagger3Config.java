package com.becxagy.book.api.infra.in.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Bidding API",
        version = "Versão 1.0",
        description = "API for managing biddings and extract checklists")
)
@Configuration
public class Swagger3Config {
}