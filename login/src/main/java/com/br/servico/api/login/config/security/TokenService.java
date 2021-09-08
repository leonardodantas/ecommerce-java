package com.br.servico.api.login.config.security;


import com.br.servico.api.login.models.response.TokenResponseDTO;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Autowired
    private HttpServletRequest request;

    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_NOT_EXIST = "Authorization não encontrado";

    public TokenResponseDTO generateTokenResponse(Authentication authentication) {
        ResourceOwner resourceOwner = (ResourceOwner) authentication.getPrincipal();
        return TokenResponseDTO.of(resourceOwner, secret,expiration);
    }

    public String getIdUser(){
        String token = this.getToken();
        this.isTokenValid(token);
        return this.getIdUser(token);
    }

    private void isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private String getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return String.valueOf(claims.getSubject());
    }

    private String getToken() {
        String token = this.request.getHeader(AUTHORIZATION);
        if (Strings.isNullOrEmpty(token)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AUTHORIZATION_NOT_EXIST);
        }
        return token;
    }
}