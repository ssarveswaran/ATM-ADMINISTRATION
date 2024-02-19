package com.example.atm.project.Configuration;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
public class SwaggerConfig
{
    @Bean
    public OpenAPI usersMicroserviceOpenAPI()
    {
        return new OpenAPI()
                .info(new Info().title("ATM ADMINISTRATION MANAGEMENT")
                        .description("Efficient cash management functionalities includes tasks such as user management, transaction monitoring, cash logistics, and security measures. \n")
                        .version("1.0"));
    }
}