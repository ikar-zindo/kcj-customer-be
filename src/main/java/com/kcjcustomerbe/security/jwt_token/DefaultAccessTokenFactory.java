package com.kcjcustomerbe.security.jwt_token;

import com.kcjcustomerbe.security.entity.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

public class DefaultAccessTokenFactory implements Function<Token, Token> {

    private Duration tokenTtl = Duration.ofMinutes(5);

    @Override
    public Token apply(Token token) {
        var now = Instant.now();
        return new Token(token.id(), token.subject(),
                token.authorities().stream()
                        .filter(authority -> authority.startsWith("GRANT_"))
                        .map(authority -> authority.substring(6))
                        .toList(), now, now.plus(this.tokenTtl));
    }

    public void setTokenTtl(Duration tokenTtl) {
        this.tokenTtl = tokenTtl;
    }
}
