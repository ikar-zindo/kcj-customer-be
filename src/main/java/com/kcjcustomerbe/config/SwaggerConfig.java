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
            .addSecurityItem(new SecurityRequirement().addList("Cookie Authentication"))
            .components(new Components().addSecuritySchemes("Cookie Authentication", createCookieAuthScheme()));
//            .info(new io.swagger.v3.oas.models.info.Info().title("My REST API")
//                  .description("Some custom description of API.")
//                  .version("1.0")
//                  .contact(new io.swagger.v3.oas.models.info.Contact().name("Ivan Bukrieiev").url("https://github.com/ikar-zindo?tab=repositories"))
//                  .license(new io.swagger.v3.oas.models.info.License().name("License of API").url("API license URL")));
   }

   private SecurityScheme createCookieAuthScheme() {
      return new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.COOKIE)
            .name("__Host-auth-token");
   }

//   @Bean
//   public OpenAPI customOpenAPI() {
//      return new OpenAPI()
//            .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
//            .components(new io.swagger.v3.oas.models.Components()
//                  .addSecuritySchemes("basicAuth", new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("basic")));
//   }

//   private ApiKey apiKey() {
//      return new ApiKey("apiKey", "api_key", "header");
//   }
//
//   private SecurityContext securityContext() {
//      return SecurityContext.builder()
//            .securityReferences(defaultAuth())
//            .forPaths(PathSelectors.regex("/auth.*")) // Указываем путь, для которого применяется аутентификация
//            .build();
//   }
//
//   List<SecurityReference> defaultAuth() {
//      AuthorizationScope authorizationScope
//            = new AuthorizationScope("global", "accessEverything");
//      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//      authorizationScopes[0] = authorizationScope;
//      return Arrays.asList(
//            new SecurityReference("apiKey", authorizationScopes));
//   }
}


//   @Bean
//   public RequestInterRequestInterceptor cookieAuthInterceptor() {
//      return requestTemplate -> {
//         // Извлекаем куку из HttpServletRequest
//         HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//         String cookieValue = servletRequest.getHeader(HttpHeaders.COOKIE);
//
//         // Добавляем куку в HttpHeaders
//         if (cookieValue != null && !cookieValue.isEmpty()) {
//            requestTemplate.header(HttpHeaders.COOKIE, cookieValue);
//         }
//      };
//   }
//}


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