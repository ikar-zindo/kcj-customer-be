package com.kcjcustomerbe.security.service;

import com.kcjcustomerbe.security.entity.Token;
import com.kcjcustomerbe.security.entity.TokenUser;
import com.kcjcustomerbe.security.exception.SecurityErrorMessage;
import com.kcjcustomerbe.security.repo.DeactivatedTokenRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.time.Instant;
import java.util.List;

public class TokenAuthenticationUserDetailsService
      implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

   private final DeactivatedTokenRepository deactivatedTokenRepository;

   public TokenAuthenticationUserDetailsService(DeactivatedTokenRepository deactivatedTokenRepository) {
      this.deactivatedTokenRepository = deactivatedTokenRepository;
   }

   @Override
   public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken)
         throws UsernameNotFoundException {
      if (authenticationToken.getPrincipal() instanceof Token token) {

         Boolean isTokenDeactivated = deactivatedTokenRepository.findById(token.id()).isPresent();

         boolean isTokenValid = !isTokenDeactivated && token.expiresAt().isAfter(Instant.now());

         List<SimpleGrantedAuthority> authorities = token.authorities().stream()
               .map(SimpleGrantedAuthority::new)
               .toList();

         return new TokenUser(
               token.subject(),
               "nopassword",
               true,
               true,
               isTokenValid,
               true,
               authorities,
               token
         );
      }

      throw new UsernameNotFoundException(SecurityErrorMessage.PRINCIPAL_MUST_BE_TOKEN);
   }
}