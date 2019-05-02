package com.itu.keystroke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableJpaAuditing
@SpringBootApplication
public class KeyStrokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyStrokeApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                CorsRegistration corsRegistration = registry.addMapping("/**")
                        .allowCredentials(false) //https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS#Requests_with_credentials
                        .maxAge(3600)
                        .allowedHeaders("*")
                        .allowedMethods("*");
                corsRegistration.allowedOrigins("*");
            }

        };
    }
}