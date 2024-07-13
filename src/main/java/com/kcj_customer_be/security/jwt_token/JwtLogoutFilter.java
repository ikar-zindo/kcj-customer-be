package com.kcj_customer_be.security.jwt_token;

import com.kcj_customer_be.security.entity.DeactivatedToken;
import com.kcj_customer_be.security.entity.TokenUser;
import com.kcj_customer_be.security.repo.DeactivatedTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.sql.Timestamp;

public class JwtLogoutFilter extends OncePerRequestFilter {

   private RequestMatcher requestMatcher = new AntPathRequestMatcher("/jwt/logout", HttpMethod.POST.name());

   private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

   private final DeactivatedTokenRepository deactivatedTokenRepository;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {
      if (this.requestMatcher.matches(request)) {
         if (this.securityContextRepository.containsContext(request)) {
            var context = this.securityContextRepository.loadDeferredContext(request).get();
            if (context != null && context.getAuthentication() instanceof PreAuthenticatedAuthenticationToken &&
                  context.getAuthentication().getPrincipal() instanceof TokenUser user &&
                  context.getAuthentication().getAuthorities()
                        .contains(new SimpleGrantedAuthority("JWT_LOGOUT"))) {

               DeactivatedToken deactivatedToken = new DeactivatedToken();
               deactivatedToken.setId(user.getToken().id());
               deactivatedToken.setKeepUntil(Timestamp.from(user.getToken().expiresAt()));

               deactivatedTokenRepository.save(deactivatedToken);

               response.setStatus(HttpServletResponse.SC_NO_CONTENT);
               return;
            }
         }

         throw new AccessDeniedException("User must be authenticated with JWT");
      }

      filterChain.doFilter(request, response);
   }

   public JwtLogoutFilter(DeactivatedTokenRepository deactivatedTokenRepository) {
      this.deactivatedTokenRepository = deactivatedTokenRepository;
   }

   public void setRequestMatcher(RequestMatcher requestMatcher) {
      this.requestMatcher = requestMatcher;
   }

   public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
      this.securityContextRepository = securityContextRepository;
   }
}
