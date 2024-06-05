package com.kcjcustomerbe.config;

import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

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
@EnableSwagger2
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
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(PACKAGE_NAME))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(Arrays.asList(new ApiKey("CookieAuth", HttpHeaders.COOKIE, In.HEADER.name())))
            .tags(new Tag(CART, "API for working with cart service"))
            .tags(new Tag(CART_PRODUCT, "API for working with cartProduct service"))
            .tags(new Tag(CUSTOMER, "API for working with customer service"))
            .tags(new Tag(ORDER, "API for working with order service"))
            .tags(new Tag(ORDER_PRODUCT, "API for working with orderProduct service"))
            .tags(new Tag(PRODUCT, "API for working with product service"))
            .tags(new Tag(RESTAURANT, "API for working with restaurant service"))
            .tags(new Tag(REVIEW, "API for working with review service"));
   }
}


//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//   @Bean
//   public OpenAPI openAPI() {
//      return new OpenAPI()
//              .addSecurityItem(new SecurityRequirement()
//                      .addList("Basic Authentication"))
//              .components(new Components()
//                      .addSecuritySchemes("Basic Authentication", createAPIKeyScheme()))
//              .info(new Info()
//                      .title("REST API K-CURRY-JIB")
//                      .description("RESTful API for managing awesome features.")
//                      .version("1.2.0")
//                      .contact(new Contact()
//                              .name("K-Curry Jib")
//                              .email("root@k-curry-jib.com")
//                              .url("https://github.com/ikar-zindo?tab=repositories"))
//                      .license(new License()
//                              .name("License of API").url("API license URL")));
//   }
//
//   private SecurityScheme createAPIKeyScheme() {
//      return new SecurityScheme()
//              .type(SecurityScheme.Type.HTTP)
//              .scheme("basic");
//   }
//}