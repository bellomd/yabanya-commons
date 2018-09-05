package com.yabanya.commons.web.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfigurerAdapter implements WebMvcConfigurer {

    private static final String CORS_PATTERN = "/**";

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping(CORS_PATTERN)
                .allowedOrigins("*")
                .allowedHeaders("origin", "content-type", "accept", "authenticationTokenId", "sourceAddress")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
                .allowCredentials(true)
                .maxAge(1209600);
    }
}
