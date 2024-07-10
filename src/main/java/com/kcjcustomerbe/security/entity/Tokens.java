package com.kcjcustomerbe.security.entity;

public record Tokens(String accessToken, String accessTokenExpiry,
                     String refreshToken, String refreshTokenExpire) {
}
