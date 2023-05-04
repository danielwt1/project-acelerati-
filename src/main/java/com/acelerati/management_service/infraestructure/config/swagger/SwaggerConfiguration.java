package com.acelerati.management_service.infraestructure.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class SwaggerConfiguration {
    //config for open API for document bearer token

    @Bean
    public OpenAPI myAPI() {
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("my-custom-header", "my-custom-value");

        SecurityScheme bearerScheme = new SecurityScheme()
                .name("jwt")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("The token is necessary to be able to use the available functionalities")
                .extensions(extensions);

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", bearerScheme))
                .info(new Info()
                        .title("Management_Service API")
                        .description("Documentation of API v.1.0")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearer-key", Arrays.asList("read", "write")));
    }

}
