/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author johnson3yo
 */
@Configuration
@EnableSwagger2
public class DocsConfiguration {
    
     @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("business-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pevi.core.controllers"))
                .paths(paths())
                .build().apiInfo(metaData());
    }

    private Predicate<String> paths() {
        return or(
                regex("/api/product.*"),
                regex("/api/orders.*"),
                regex("/api/category.*")
                );

    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Pevi Ecommerce",
                "REST API for Pevi Food Services",
                "1.0",
                "Terms of service",
                new Contact("Jonson Eyo", "https://crowninteractive.com", "johnson.eyo@crowninteractive.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
