package com.echoad.Customers;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CustomersApplication {
    private final Environment env;

    public CustomersApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                //this is not a good way to do it. this is just for demo purpose
                registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
            }
        };
    }




    // Application Info
    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI()
                .info(apiInfo());
    }


    public Info apiInfo() {
        return new Info()
                .title(env.getProperty("api.title"))
                .description("This is a customer service")
                .version(env.getProperty("api.version"))
                .contact(new Contact().name("EchoAd").email("adrianeea.ae@gmail.com")
                        .url("https://www.linkedin.com/in/adrian-estevez-b89b50b8/"));
    }




}


