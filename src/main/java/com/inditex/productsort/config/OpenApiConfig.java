package com.inditex.productsort.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Sorting API")
                        .version("1.0")
                        .description("REST API for sorting products based on configurable criteria like sales units and stock availability.")
                        .contact(new Contact()
                                .name("Leandro Lupano")
                                .url("https://github.com/leandroLupano/product-sorting-service")));
    }
}
