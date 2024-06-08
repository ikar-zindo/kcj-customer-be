package com.kcjcustomerbe.cookie_auth;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.time.Instant;
import java.util.List;


/**
 * Здесь заоодно проверятся, что Token не заблокирован
 */
public class TokenAuthenticationUserDetailsService
        implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final JdbcTemplate jdbcTemplate;

    public TokenAuthenticationUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken)
          throws UsernameNotFoundException {
       if (authenticationToken.getPrincipal() instanceof Token token) {
          // Проверка, деактивирован ли токен
          Boolean isTokenDeactivated = this.jdbcTemplate.queryForObject("""
                SELECT EXISTS(SELECT token_id FROM deactivated_tokens WHERE token_id = ?)
                """, Boolean.class, token.id());

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

       throw new UsernameNotFoundException("Principal must be of type Token");


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
