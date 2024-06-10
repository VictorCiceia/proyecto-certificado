package com.project.certified.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Proyecto Final ADA SCHOOL - Certifiación").version("1.0.0").description("Curso de Spring boot"));
    }
}
