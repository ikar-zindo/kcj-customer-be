package com.kcj_customer_be.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
      info = @Info(
            title = "K-Curry Jib Customer Backend",
            description = "REST API for server application. " +
                  "This application implements a functional food delivery service.",
            version = "1.3.0",
            contact = @Contact(
                  name = "Ivan Bukrieiev",
                  url = "https://github.com/ikar-zindo?tab=repositories"
            ),
            license = @License(
                  name = "License of API",
                  url = "API license URL"
            )
      )
)
@Configuration
public class SwaggerConfig {
   @Value("${swagger.packageName:com.kcj_customer_be}")
   private String PACKAGE_NAME;

   public static final String CART = "cart controller";
   public static final String CART_PRODUCT = "cartProduct controller";
   public static final String CUSTOMER = "customer controller";
   public static final String ORDER = "order controller";
   public static final String ORDER_PRODUCT = "orderProduct controller";
   public static final String PRODUCT = "product controller";
   public static final String RESTAURANT = "restaurant controller";
   public static final String REVIEW = "review controller";

   @Bean
   public GroupedOpenApi publicApi() {
      return GroupedOpenApi.builder()
            .group("public")
            .packagesToScan(PACKAGE_NAME)
            .addOpenApiCustomiser(openApi -> {
               openApi.addTagsItem(new Tag().name(CART).description("API for working with cart controller"));
               openApi.addTagsItem(new Tag().name(CART_PRODUCT).description("API for working with cart products controller"));
               openApi.addTagsItem(new Tag().name(CUSTOMER).description("API for working with customers controller"));
               openApi.addTagsItem(new Tag().name(ORDER).description("API for working with orders controller"));
               openApi.addTagsItem(new Tag().name(ORDER_PRODUCT).description("API for working with order products controller"));
               openApi.addTagsItem(new Tag().name(PRODUCT).description("API for working with products controller"));
               openApi.addTagsItem(new Tag().name(RESTAURANT).description("API for working with user restaurants controller"));
               openApi.addTagsItem(new Tag().name(REVIEW).description("API for working with reviews controller"));
            })
            .build();
   }

   @Bean
   public OpenAPI openAPI() {
      return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
   }

   private SecurityScheme createAPIKeyScheme() {
      return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
   }
}