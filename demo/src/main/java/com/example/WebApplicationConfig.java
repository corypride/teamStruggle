package main.java.com.example;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

    // Create object that will allow app to access filter
    @Bean
    // makes it a Spring-managed class
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    // Register filter with Spring so it will intercept requests
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authenticationFilter() );
    }
}
