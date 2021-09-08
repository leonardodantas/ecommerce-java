package com.br.servico.api.login.models.response;

import com.br.servico.api.login.config.security.ResourceOwner;
import com.br.servico.api.login.models.dto.DateTokenDTO;
import com.br.servico.api.login.utils.DateUtils;
import lombok.Getter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;

@Getter
public class TokenResponseDTO {

    private final String token;
    private final LocalDateTime created;
    private final LocalDateTime expiration;

    private TokenResponseDTO(String token, LocalDateTime created, LocalDateTime expiration) {
        this.token = token;
        this.created = created;
        this.expiration = expiration;
    }

    public static TokenResponseDTO of(ResourceOwner resourceOwner, String secret, long expiration) {
        DateTokenDTO dateTokenDTO = DateUtils.generateNowWithExpiration(expiration);
        String token = Jwts.builder()
                .setIssuer("SERVIÇO BOOKSERVER")
                .setSubject(resourceOwner.getId())
                .setIssuedAt(dateTokenDTO.getNow())
                .setExpiration(dateTokenDTO.getExpiration())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return new TokenResponseDTO(token, DateUtils.dateToLocalDateTime(dateTokenDTO.getNow()), DateUtils.dateToLocalDateTime(dateTokenDTO.getExpiration()));
    }

}
