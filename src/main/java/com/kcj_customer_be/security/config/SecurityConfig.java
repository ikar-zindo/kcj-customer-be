package com.kcj_customer_be.security.config;

import com.kcj_customer_be.security.exception.ExceptionDeniedHandler;
import com.kcj_customer_be.security.exception.UnauthorizedAuthenticationEntryPoint;
import com.kcj_customer_be.security.jwt_token.*;
import com.kcj_customer_be.security.repo.DeactivatedTokenRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import java.text.ParseException;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//   private final UserDetailsServiceImpl customerService;

   @Autowired
   private final ExceptionDeniedHandler exceptionDeniedHandler;

   @Autowired
   private final UnauthorizedAuthenticationEntryPoint unauthorizedAuthenticationEntryPoint;

   @Bean
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

//   @Bean
//   public DaoAuthenticationProvider authenticationProvider() {
//      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//      provider.setUserDetailsService(customerService);
//      provider.setPasswordEncoder(bCryptPasswordEncoder());
//      return provider;
//   }

   @Bean
   public JwtAuthenticationConfigurer jwtAuthenticationConfigurer(
         @Value("${jwt.access-token-key}") String accessTokenKey,
         @Value("${jwt.refresh-token-key}") String refreshTokenKey,
         DeactivatedTokenRepository deactivatedTokenRepository
   ) throws ParseException, JOSEException {
      return new JwtAuthenticationConfigurer()
            .accessTokenStringSerializer(new AccessTokenJwsStringSerializer(
                  new MACSigner(OctetSequenceKey.parse(accessTokenKey))
            ))
            .refreshTokenStringSerializer(new RefreshTokenJweStringSerializer(
                  new DirectEncrypter(OctetSequenceKey.parse(refreshTokenKey))
            ))
            .accessTokenStringDeserializer(new AccessTokenJwsStringDeserializer(
                  new MACVerifier(OctetSequenceKey.parse(accessTokenKey))
            ))
            .refreshTokenStringDeserializer(new RefreshTokenJweStringDeserializer(
                  new DirectDecrypter(OctetSequenceKey.parse(refreshTokenKey))
            ))
            .deactivatedTokenRepository(deactivatedTokenRepository);
   }

   @Bean
   public SecurityFilterChain securityFilterChain(
         HttpSecurity http, JwtAuthenticationConfigurer jwtAuthenticationConfigurer) throws Exception {

      http.apply(jwtAuthenticationConfigurer);

      http
            .csrf(AbstractHttpConfigurer::disable)
            .logout(logout ->
                  logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
            )
            .sessionManagement(sessionManagement ->
                  sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizeHttpRequests ->
                  authorizeHttpRequests
                        .requestMatchers(
                              "/jwt/login",
                              "/registration").anonymous()
                        .requestMatchers(
                              "/customer/**",
                              "/cart/**",
                              "/restaurant/**",
                              "/jwt/logout").hasRole("CUSTOMER")
                        .requestMatchers(
                              "/",
                              "/error",
                              "index.html",
                              "/favicon.ico",
                              "/v2/api-docs/**",
                              "/configuration/ui",
                              "/swagger-resources/",
                              "/configuration/security",
                              "/webjars/",
                              "/v3/api-docs/**",
                              "/swagger-ui.html",
                              "/api/v1/auth/",
                              "/swagger-ui/**",
                              "/review/**",
                              "/product/**").permitAll()
                        .anyRequest()
                        .authenticated()
            )
            .headers(headers ->
                  headers.cacheControl(Customizer.withDefaults()).disable())
            .httpBasic(Customizer.withDefaults())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                  .accessDeniedHandler(exceptionDeniedHandler)
                  .authenticationEntryPoint(unauthorizedAuthenticationEntryPoint));

      return http.build();
   }
}
