package com.restfulmovies.restfulmovies.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Yordan Stoyanov",
                        email = "yordan.y.stoyanov@gmail.com",
                        url = "https://github.com/danny-stoya"
                ),
                description = "OpenApi description for Restful Movies",
                title = "OpenApi Specification",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local",
                        url = "http://localhost:8080"
                )
        },
        security = @SecurityRequirement(
                name = "JWT Authorization"
        )
)
@SecurityScheme(
        name = "JWT Authorization",
        description = "Enter a valid access token.",
        scheme = "Bearer",
        type = HTTP,
        bearerFormat = "JWT",
        in = HEADER
)
public class OpenApiConfig {
}
