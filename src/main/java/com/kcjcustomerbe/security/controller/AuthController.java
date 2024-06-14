package com.kcjcustomerbe.security.controller;

import com.kcjcustomerbe.security.entity.AuthRequest;
import com.kcjcustomerbe.security.entity.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class AuthController {


   private AuthenticationManager authenticationManager;
//
//   @PostMapping("/tokens")
//   public ResponseEntity<Tokens> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
//
//
//
//
//   }
}
