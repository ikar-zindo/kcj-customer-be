package com.kcjcustomerbe.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
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
            description = "",
            version = "1.0.0",
            contact = @Contact(
                  name = "Ivan Bukrieiev",
                  url = "https://github.com/ikar-zindo?tab=repositories"
            )
      )
)
@Configuration
public class SwaggerConfig {
   @Value("${swagger.packageName:com.kcjcustomerbe}")
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
               openApi.addTagsItem(new Tag().name(CART).description("API for working with cart service"));
               openApi.addTagsItem(new Tag().name(CART_PRODUCT).description("API for working with cart products service"));
               openApi.addTagsItem(new Tag().name(CUSTOMER).description("API for working with customers service"));
               openApi.addTagsItem(new Tag().name(ORDER).description("API for working with orders service"));
               openApi.addTagsItem(new Tag().name(ORDER_PRODUCT).description("API for working with order products service"));
               openApi.addTagsItem(new Tag().name(PRODUCT).description("API for working with products service"));
               openApi.addTagsItem(new Tag().name(RESTAURANT).description("API for working with user restaurants service"));
               openApi.addTagsItem(new Tag().name(REVIEW).description("API for working with reviews service"));
            })
            .build();
   }

   @Bean
   public OpenAPI openAPI() {
      return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(new io.swagger.v3.oas.models.info.Info().title("My REST API")
                  .description("Some custom description of API.")
                  .version("1.0")
                  .contact(new io.swagger.v3.oas.models.info.Contact().name("Ivan Bukrieiev").url("https://github.com/ikar-zindo?tab=repositories"))
                  .license(new io.swagger.v3.oas.models.info.License().name("License of API").url("API license URL")));
   }

   private SecurityScheme createAPIKeyScheme() {
      return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
   }
}