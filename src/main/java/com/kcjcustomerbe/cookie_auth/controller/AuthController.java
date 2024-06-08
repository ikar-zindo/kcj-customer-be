package com.kcjcustomerbe.cookie_auth.controller;

import com.kcjcustomerbe.cookie_auth.AuthRequest;
import com.kcjcustomerbe.cookie_auth.TokenCookieSessionAuthenticationStrategy;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

   @Autowired
   private AuthenticationManager authenticationManager;

//   @Autowired
   private TokenCookieSessionAuthenticationStrategy tokenCookieSessionAuthenticationStrategy;

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      tokenCookieSessionAuthenticationStrategy.onAuthentication(authentication, null, response);

      return ResponseEntity.ok().build();
   }
}
