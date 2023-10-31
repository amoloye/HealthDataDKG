package com.example.healthdkg.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Add your allowed origins here. "*" allows all origins.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add the allowed HTTP methods.
                .allowedHeaders("*"); // Add the allowed headers.
    }
}
