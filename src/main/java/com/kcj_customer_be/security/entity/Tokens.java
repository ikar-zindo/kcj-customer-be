package com.kcj_customer_be.security.entity;

public record Tokens(String accessToken, String accessTokenExpiry,
                     String refreshToken, String refreshTokenExpire) {
}
