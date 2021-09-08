package com.br.servico.api.login.services.impl;

import com.br.servico.api.login.config.security.AuthenticationService;
import com.br.servico.api.login.config.security.TokenService;
import com.br.servico.api.login.models.request.UserLoginRequestDTO;
import com.br.servico.api.login.models.response.TokenResponseDTO;
import com.br.servico.api.login.services.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Override
    public TokenResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO) {
        Authentication authenticate = authenticationService.authenticatedUser(userLoginRequestDTO.toUsernamePasswordAuthenticationToken());
        return tokenService.generateTokenResponse(authenticate);
    }
}
