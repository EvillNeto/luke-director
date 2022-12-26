package dev.evilasio.lukedirector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class ApiCofiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Luke, I am your Director!")
                        .license(new License().name("H2 DataBase")
                                .url("http://localhost:8080/h2-console")));
    }
}
