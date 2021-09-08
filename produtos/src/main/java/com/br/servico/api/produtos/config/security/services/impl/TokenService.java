package com.br.servico.api.produtos.config.security.services.impl;

import com.br.servico.api.produtos.config.security.services.ITokenService;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenService implements ITokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(token)) {
            return null;
        }
        return token;
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return String.valueOf(claims.getSubject());
    }
}