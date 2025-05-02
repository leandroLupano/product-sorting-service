package com.inditex.productsort.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${custom.openapi.title}")
    private String title;

    @Value("${custom.openapi.version}")
    private String version;

    @Value("${custom.openapi.description}")
    private String description;

    @Value("${custom.openapi.contact.name}")
    private String contactName;

    @Value("${custom.openapi.contact.url}")
    private String contactUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                        .contact(new Contact()
                                .name(contactName)
                                .url(contactUrl)));
    }
}
