package com.kcjcustomerbe.config;

import com.kcjcustomerbe.security.jwt_token.*;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.text.ParseException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//   private final UserDetailsServiceImpl customerService;

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
         JdbcTemplate jdbcTemplate
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
            .jdbcTemplate(jdbcTemplate);
   }

   @Bean
   public SecurityFilterChain securityFilterChain(
         HttpSecurity http, JwtAuthenticationConfigurer jwtAuthenticationConfigurer) throws Exception {

      http.apply(jwtAuthenticationConfigurer);

      http
            .csrf(AbstractHttpConfigurer::disable)
//            .logout(logout -> logout
//                  .logoutUrl("/jwt/logout")
//                  .invalidateHttpSession(true)
//                  .deleteCookies("JSESSIONID")
//                  .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
//            )
            .sessionManagement(sessionManagement ->
                  sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                              .requestMatchers(
//                                    "/auth/login",
                                    "/login",
                                    "/registration").anonymous()
                              .requestMatchers(
                                    "/customer/**",
                                    "/cart/**",
                                    "/product/**",
                                    "/manager.html").hasRole("CUSTOMER")
                              .requestMatchers(
                                    "/",
                                    "index.html",
                                    "/v2/api-docs/**",
                                    "/configuration/ui",
                                    "/swagger-resources/",
                                    "/configuration/security",
                                    "/webjars/",
                                    "/v3/api-docs/**",
                                    "/swagger-ui.html",
                                    "/api/v1/auth/",
                                    "/swagger-ui/**",
                                    "/restaurant/**").permitAll()
                              .anyRequest()
                              .authenticated()
            )
            .headers(headers -> headers.cacheControl(Customizer.withDefaults()).disable())
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());

      return http.build();
   }

//   @Bean
//   public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
//      // TODO: реализовать с использоавнием CustomerRepository
//
//      String userQuery = "SELECT * FROM customers WHERE email = ?";
//      return username -> jdbcTemplate.query(userQuery,
//            (rs, i) -> User.builder()
//                  .username(rs.getString("email"))
//                  .password(rs.getString("password"))
//                  .authorities(Collections.singleton(new SimpleGrantedAuthority(rs.getString("role"))))
//                  .build(), username).stream().findFirst().orElse(null);
//   }
}
