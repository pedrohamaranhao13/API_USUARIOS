package br.com.phamtecnologia.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI CusOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Usuários")
                        .description("Documentação da API de Usuários com Spring Boot e Swagger.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Pedro Maranhão")
                                .email("pedro.maranhao@yahoo.com.br")
                                .url("hhtps://www.phamtecnologia.com.br")
                ));
    }
}
