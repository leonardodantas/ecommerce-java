package com.br.servico.api.produtos.config.security.services;

import javax.servlet.http.HttpServletRequest;

public interface ITokenService {
    String getToken(HttpServletRequest request);
    boolean isTokenValid(String token);
    String getIdUser(String token);
}
