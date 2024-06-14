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

//   private final JdbcTemplate jdbcTemplate;

   private final DeactivatedTokenRepository deactivatedTokenRepository;

   public TokenAuthenticationUserDetailsService(
//         JdbcTemplate jdbcTemplate
         DeactivatedTokenRepository deactivatedTokenRepository
   ) {
//      this.jdbcTemplate = jdbcTemplate;
      this.deactivatedTokenRepository = deactivatedTokenRepository;
   }

   @Override
   public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken)
         throws UsernameNotFoundException {
      if (authenticationToken.getPrincipal() instanceof Token token) {
         // Проверка, деактивирован ли токен

         Boolean isTokenDeactivated = deactivatedTokenRepository.findById(token.id()).isPresent();

//         UUID tokenId = token.id();
//         ByteBuffer tokenIdBB = ByteBuffer.wrap(new byte[16]);
//         tokenIdBB.putLong(tokenId.getMostSignificantBits());
//         tokenIdBB.putLong(tokenId.getLeastSignificantBits());
//
//         Boolean isTokenDeactivated = this.jdbcTemplate.queryForObject("""
//               SELECT EXISTS(SELECT token_id FROM deactivated_tokens WHERE token_id = ?)
//               """, Boolean.class, tokenIdBB.array());

         // Проверка, истек ли срок действия токена
         boolean isTokenValid = !isTokenDeactivated && token.expiresAt().isAfter(Instant.now());

         // Преобразование списка авторитетов токена в SimpleGrantedAuthority
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


//    @Override
//    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken)
//            throws UsernameNotFoundException {
//        if (authenticationToken.getPrincipal() instanceof Token token) {
//            return new TokenUser(token.subject(), "nopassword", true, true,
//                    !this.jdbcTemplate.queryForObject("""
//                            select exists(select id from t_deactivated_token where id = ?)
//                            """, Boolean.class, token.id()) &&
//                            token.expiresAt().isAfter(Instant.now()),
//                    true,
//                    token.authorities().stream()
//                            .map(SimpleGrantedAuthority::new)
//                            .toList(), token);
//        }
//
//        throw new UsernameNotFoundException("Principal must me of type Token");
//    }
//}
