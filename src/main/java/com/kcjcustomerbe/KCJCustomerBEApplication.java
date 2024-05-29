package com.kcjcustomerbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KCJCustomerBEApplication {

   public static void main(String[] args) {
      SpringApplication.run(KCJCustomerBEApplication.class, args);
   }

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      return http
//            .httpBasic().and()
//            .authorizeHttpRequests()
//            .anyRequest().authenticated().and()
//            .build();
//   }
//
//   @Bean
//   public RouterFunction<ServerResponse> routerFunction() {
//      return RouterFunctions.route()
//            .GET("/api/v4/greetings", request -> {
//               UserDetails userDetails = request.principal().map(Authentication.class::cast)
//                     .map(Authentication::getPrincipal)
//                     .map(UserDetails.class::cast)
//                     .orElseThrow();
//
//
//               return ServerResponse.ok()
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .body(Map.of("greeting", "Hello2, %s (V4)"
//                           .formatted(userDetails.getUsername())));
//            })
//            .build();
//   }
}