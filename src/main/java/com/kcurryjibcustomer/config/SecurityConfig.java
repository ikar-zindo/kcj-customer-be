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
      return http
              .csrf(AbstractHttpConfigurer::disable)
              .logout(
                      logout -> logout.logoutUrl("/logout")
                              .permitAll()
              )
              .authorizeHttpRequests(
                      requests -> requests
                              .requestMatchers(
                                      "/",
                                      "/style.css",
                                      "/img/**",
                                      "/error",
                                      "/pizzas",
                                      "/cafes",
                                      "/swagger-ui/**",
                                      "/menu")
                              .permitAll()
                              .anyRequest()
                              .authenticated()
              )
              .formLogin(
                      form -> form
                              .loginPage("/login")
                              .permitAll()
              )
              .build();
   }
}
