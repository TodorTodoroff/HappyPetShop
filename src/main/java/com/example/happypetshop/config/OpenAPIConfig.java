package com.example.happypetshop.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().
                info(new Info().
                        title("Users API").
                        version("1.0.0").
                        contact(new Contact().name("Todor Todorov").
                                email("todor.todoroff89@gmail.com")).
                        description("User API"));
    }

}
