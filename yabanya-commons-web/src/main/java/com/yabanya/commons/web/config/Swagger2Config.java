package com.yabanya.commons.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {

        final String TITLE = "Application API";
        final String DESCRIPTION = "Application API";
        final String VERSION = "Version 1.0";
        final String TERMS_OF_SERVICE_URL = "urn:tos";
        final String LICENSE = "Apache 2.0";
        final String LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0";

        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .contact(getSwaggerContact())
                .license(LICENSE)
                .licenseUrl(LICENSE_URL)
                .build();
    }

    private Contact getSwaggerContact() {

        final String NAME = "yabanya";
        final String EMAIL = "yabanyaapi@yabanya.com";
        final String URL = "http://www.yabanya.com/licenses/LICENSE-2.0";

        return new Contact(NAME, URL, EMAIL);
    }
}
