package com.stellive.music.domain.api.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "스텔라툰 API 명세서",
                description = "스텔라툰 API 명세서",
                version = "0.0.1"
        )
)
@Configuration
public class SwaggerConfig {

    private static final String BEARER_TOKEN_PREFIX = "Bearer";

    @Bean
    public OpenAPI openAPI() {
        String authorization = HttpHeaders.AUTHORIZATION;
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(authorization);
        Components components = new Components()
                .addSecuritySchemes(authorization,
                        new SecurityScheme()
                            .name(authorization)
                            .type(SecurityScheme.Type.HTTP)
                            .in(SecurityScheme.In.HEADER)
                            .scheme(BEARER_TOKEN_PREFIX)
                            .bearerFormat("JWT")
                );

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}
