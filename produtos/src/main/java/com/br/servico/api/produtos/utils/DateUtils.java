package com.br.servico.api.produtos.utils;

import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

public class DateUtils {

    private DateUtils(){}

    private static final String START_AFTER_END = "Data inicial deve ser menor que data final";
    private static final String START_BEFORE_NOW = "Data inicial deve ser maior que data atual";
    private static final String START_EQUALS_END = "Data inicial deve ser diferente de data final";

    public static void validateStartAfterEnd(LocalDateTime start, LocalDateTime end){
        if (start.isAfter(end)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, START_AFTER_END);
        }
    }

    public static void validateStartBeforeNow(LocalDateTime start){
        LocalDateTime now = LocalDateTime.now();
        if (start.isBefore(now)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, START_BEFORE_NOW);
        }
    }

    public static boolean validateDateBetweenPeriod(LocalDateTime date, LocalDateTime fistPeriodo, LocalDateTime endPeriod){
        return date.isAfter(fistPeriodo) && date.isBefore(endPeriod);
    }

    public static void validateDateEquals(LocalDateTime fistDate, LocalDateTime endDate){
        if(fistDate.equals(endDate)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, START_EQUALS_END);
        }
    }

    public static boolean validateDateBeforePeriod(LocalDateTime date, LocalDateTime fistPeriodo, LocalDateTime endPeriod){
        return date.isAfter(fistPeriodo) && date.isBefore(endPeriod);
    }
}
