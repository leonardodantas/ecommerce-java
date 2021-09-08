package com.br.servico.api.carrinho.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class HttpEntityUtils<T> {

    private static final String AUTHORIZATION = "Authorization";

    public HttpEntity<T> createHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<T>(headers);
    }

    public HttpEntity<T> createHttpEntity(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        return new HttpEntity<T>(headers);
    }
}
