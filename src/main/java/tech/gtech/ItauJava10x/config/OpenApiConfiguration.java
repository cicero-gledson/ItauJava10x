package tech.gtech.ItauJava10x.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Desafio ITAU Java10x")
                .description("Api do desafio técnico do ITAU + Java10x")
                .version("1.0.1b")
        );
    }
}
