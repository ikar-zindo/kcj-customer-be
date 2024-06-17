//package com.kcjcustomerbe.security.config;
//
//import com.kcjcustomerbe.security.entity.Token;
//import com.kcjcustomerbe.security.jwt_token.DefaultRefreshTokenFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//
//import java.util.function.Function;
//
//@Configuration
//public class TokenConfig {
//
//   @Bean
//   public Function<Authentication, Token> refreshTokenFactory() {
//      return new DefaultRefreshTokenFactory();
//   }
//}
