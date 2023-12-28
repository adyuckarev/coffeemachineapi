package com.marten.coffeemachineapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0",
                description = """
                        Приложение для управления кофеваркой.
                        Поддерживается изготовление 3 видов кофе: Капучино, Латте, Раф.
                        Для них необходимо жидкости:
                        Капучино - 200 мл,
                        Латте - 100 мл,
                        Раф - 150 мл
                           """,
                contact = @Contact(name = "Aleksey Dyukarev", email = "leshadyuckarev631@gmail.com")
        )
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }
}
