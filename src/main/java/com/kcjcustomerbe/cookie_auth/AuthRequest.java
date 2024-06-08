package com.kcjcustomerbe.cookie_auth;

import lombok.Value;

@Value
public class AuthRequest {
   String email;
   String password;
}
