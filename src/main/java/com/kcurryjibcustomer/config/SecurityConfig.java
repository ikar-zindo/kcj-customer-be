package com.kcurryjibcustomer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   @Bean
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .logout(logout ->
                      logout
                              .logoutUrl("/logout")
                              .permitAll()
                              .logoutSuccessUrl("/menu")
              )
              .authorizeHttpRequests((requests) -> requests
                              .requestMatchers(
//                                      "/**",
                                      "/",
                                      "/error",
                                      "/assets/**",
                                      "/images/**",
                                      "/swagger-ui/**",
                                      "/menu/**",
                                      "/restaurant/**"
                              )
                              .permitAll()
                              .anyRequest()
                              .authenticated()
              )
              .formLogin(form ->
                      form
                              .loginPage("/login")
                              .permitAll()
              );

      return http.build();
   }
}
