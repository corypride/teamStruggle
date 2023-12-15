package main.java.com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

    // Create object that will allow app to access filter
    @Bean
    // makes it a Spring-managed class
    public main.java.com.example.AuthenticationFilter authenticationFilter() {
        return new main.java.com.example.AuthenticationFilter();
    }

    // Register filter with Spring so it will intercept requests
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authenticationFilter() );
    }
}
