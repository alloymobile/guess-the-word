package com.alloymobile.guess.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Question Generator").description(
                        "This is a Spring Boot RESTful service using springdoc-openapi and OpenAPI 3. " +
                                "It generates questions for each class and grade"));
    }
}