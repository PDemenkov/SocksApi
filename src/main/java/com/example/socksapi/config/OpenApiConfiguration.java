package com.example.socksapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI schoolApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Socks API")
                        .version("1.0.0")
                        .description("API to manage socks"))
                .addTagsItem(new Tag().name("socks").description("Socks management API"));
    }
}
