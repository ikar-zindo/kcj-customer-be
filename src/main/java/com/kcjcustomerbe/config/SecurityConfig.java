package com.kcjcustomerbe.config;

import com.kcjcustomerbe.cookie_auth.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   @Bean
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public TokenCookieJweStringSerializer tokenCookieJweStringSerializer(
         @Value("${jwt.cookie-token-key}") String cookieTokenKey
   ) throws Exception {
      return new TokenCookieJweStringSerializer(new DirectEncrypter(
            OctetSequenceKey.parse(cookieTokenKey)
      ));
   }

   @Bean
   public TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer(
         @Value("${jwt.cookie-token-key}") String cookieTokenKey,
         JdbcTemplate jdbcTemplate
   ) throws Exception {
      return new TokenCookieAuthenticationConfigurer()
            .tokenCookieStringDeserializer(new TokenCookieJweStringDeserializer(
                  new DirectDecrypter(
                        OctetSequenceKey.parse(cookieTokenKey)
                  )
            ))
            .jdbcTemplate(jdbcTemplate);
   }

   @Bean
   public SecurityFilterChain securityFilterChain(
         HttpSecurity http,
         TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer,
         TokenCookieJweStringSerializer tokenCookieJweStringSerializer) throws Exception {
      var tokenCookieSessionAuthenticationStrategy = new TokenCookieSessionAuthenticationStrategy();
      tokenCookieSessionAuthenticationStrategy.setTokenStringSerializer(tokenCookieJweStringSerializer);

      http.httpBasic(Customizer.withDefaults())
//            .formLogin(Customizer.withDefaults())
            .addFilterAfter(new GetCsrfTokenFilter(), ExceptionTranslationFilter.class)
            .authorizeHttpRequests(authorizeHttpRequests ->
                  authorizeHttpRequests
                        .requestMatchers(
                              "/auth/login",
                              "/login",
                              "/registration").anonymous()
                        .requestMatchers(
                              "/customer/**",
                              "/cart/**").hasRole("CUSTOMER")
                        .requestMatchers(
                              "/",
//                              "/**",
                              "index.html",
//                              "/auth/**",
                              "/v2/api-docs/**",
                              "/configuration/ui",
                              "/swagger-resources/",
                              "/configuration/security",
                              "/webjars/",
                              "/v3/api-docs/**",
                              "/swagger-ui.html",
                              "/api/v1/auth/",
                              "/swagger-ui/**",
                              "/product/**",
                              "/restaurant/**").permitAll()
                        .anyRequest()
                        .authenticated()
            )
            .sessionManagement(sessionManagement -> sessionManagement
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .sessionAuthenticationStrategy(tokenCookieSessionAuthenticationStrategy))
            .csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository())
                  .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                  .sessionAuthenticationStrategy((authentication, request, response) -> {
                  }));

      http.apply(tokenCookieAuthenticationConfigurer);

      return http.build();
   }

   @Bean
   public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
      // TODO: реализовать с использоавнием CustomerRepository

      String userQuery = "SELECT * FROM customers WHERE email = ?";
      return username -> jdbcTemplate.query(userQuery,
            (rs, i) -> User.builder()
                  .username(rs.getString("email"))
                  .password(rs.getString("password"))
                  .authorities(Collections.singleton(new SimpleGrantedAuthority(rs.getString("role"))))
                  .build(), username).stream().findFirst().orElse(null);
   }

   @Bean
   public AuthenticationManager authenticationManager(
         AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }
}
