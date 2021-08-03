package one.digitalinnovation.PersonAPI.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPIConfig(){
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        Info info = new Info();
        info
                .title("API REST Java")
                .description("People Management API," +
                        " developed in Java using Spring Boot.")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("Moares Oliveira")
                        .url("https://github.com/MoaresOliveira")
                        .email("moaresoliveira@gmail.com"));
        return info;
    }
}
