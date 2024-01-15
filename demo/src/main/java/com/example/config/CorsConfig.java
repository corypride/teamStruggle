package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")  // Use allowedOriginPatterns
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
                        .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization", "X-Requested-With", "X-CSRF-Token")
                        .exposedHeaders("X-Requested-With", "Authorization")
                        .allowCredentials(true);
            }
        };
    }
}