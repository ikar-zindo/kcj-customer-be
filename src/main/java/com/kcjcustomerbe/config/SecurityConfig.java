package com.kcjcustomerbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableJdbcHttpSession
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
              .authorizeHttpRequests(auth -> auth
                              .requestMatchers(
                                      "/**",
                                      "/",
                                      "/error",
                                      "/assets/**",
                                      "/images/**",
                                      "/menu/**",
                                      "/restaurant/**",
                                      "/rest/**",
                                      "/rest/product/**",
                                      "/swagger-ui.html",
                                      "/swagger-ui/**",
                                      "/v3/api-docs/**",
                                      "/api/v1/auth/"
                              )
                              .permitAll()
//                              .requestMatchers(
//                                      "/h2-console/**")
//                              .permitAll()
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




//   @Bean
//   public EmbeddedDatabase dataSource() {
//      return new EmbeddedDatabaseBuilder()
//              .setType(EmbeddedDatabaseType.H2)
//              .addScript("org/springframework/session/jdbc/schema-h2.sql").build();
//   }
//
//   @Bean
//   public PlatformTransactionManager transactionManager(DataSource dataSource) {
//      return new DataSourceTransactionManager(dataSource);
//   }
}
