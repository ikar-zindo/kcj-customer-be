package com.kcj_customer_be.security.jwt_token;

import com.kcj_customer_be.security.entity.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.UUID;
import java.util.function.Function;

public class DefaultRefreshTokenFactory implements Function<Authentication, Token> {

   private Duration tokenTtl = Duration.ofDays(30);

   @Override
   public Token apply(Authentication authentication) {
      var authorities = new LinkedList<String>();
      authorities.add("JWT_REFRESH");
      authorities.add("JWT_LOGOUT");
      authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .map(authority -> "GRANT_" + authority)
            .forEach(authorities::add);

      var now = Instant.now();
      return new Token(
            UUID.randomUUID(),
            authentication.getName(), authorities,
            now,
            now.plus(this.tokenTtl));
   }

   public void setTokenTtl(Duration tokenTtl) {
      this.tokenTtl = tokenTtl;
   }
}
