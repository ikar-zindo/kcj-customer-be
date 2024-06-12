package com.kcjcustomerbe.security.jwt_token;

public record Tokens(String accessToken, String accessTokenExpiry,
                     String refreshToken, String refreshTokenExpire) {
}
