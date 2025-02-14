package com.mnov34.CUBES4solo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Annuaire d'entreprise")
                        .version("1.0")
                        .description("API pour voir le contact des employées et administrer leur infos."));
    }
}
