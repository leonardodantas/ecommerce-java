package com.br.servico.api.login.models.dto;

import java.time.Instant;
import java.util.Date;

public class DateTokenDTO {

    private final Instant now;
    private final Instant expiration;

    private DateTokenDTO(Instant now, Instant expiration) {
        this.now = now;
        this.expiration = expiration;
    }

    public static DateTokenDTO of(Instant now, Instant expiration) {
        return new DateTokenDTO(now, expiration);
    }

    public Date getExpiration() {
        return Date.from(expiration);
    }

    public Date getNow() {
        return Date.from(now);
    }
}
