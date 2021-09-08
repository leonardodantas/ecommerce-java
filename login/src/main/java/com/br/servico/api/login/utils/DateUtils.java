package com.br.servico.api.login.utils;

import com.br.servico.api.login.models.dto.DateTokenDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant()
                .atZone(getZoneIdSystemDefault())
                .toLocalDateTime();
    }

    public static DateTokenDTO generateNowWithExpiration(long expiration){
        Instant now = Instant.now();
        Instant instant = now.plusMillis(expiration);
        return DateTokenDTO.of(now,instant);
    }

    private static ZoneId getZoneIdSystemDefault(){
        return ZoneId.systemDefault();
    }
}
