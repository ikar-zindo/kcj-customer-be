package com.kcjcustomerbe.cookie_auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @see TokenCookieAuthenticationConverter
 * Извлекает из запроса текущую сессионную Cookie ->
 * Преобразует её в Token
 * А из Token получает запрос на аутентификацию ->
 * @see PreAuthenticatedAuthenticationToken
 * Который в дальнейшем при помощи ->
 * @see org.springframework.security.authentication.AuthenticationManager ->
 * Будет передан провайдеру аутентификации ->
 * @see org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider
 * Который уже в процессе обработки запроса потребует компонент который будет преобразовывать ->
 * @see PreAuthenticatedAuthenticationToken ->
 * в информацию о пользователи, которая находится в ->
 * @see TokenAuthenticationUserDetailsService
 */
public class TokenCookieAuthenticationConverter implements AuthenticationConverter {

    private final Function<String, Token> tokenCookieStringDeserializer;

    public TokenCookieAuthenticationConverter(Function<String, Token> tokenCookieStringDeserializer) {
        this.tokenCookieStringDeserializer = tokenCookieStringDeserializer;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Stream.of(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("__Host-auth-token"))
                    .findFirst()
                    .map(cookie -> {
                        var token = this.tokenCookieStringDeserializer.apply(cookie.getValue());
                        return new PreAuthenticatedAuthenticationToken(token, cookie.getValue());
                    })
                    .orElse(null);
        }

        return null;
    }
}
