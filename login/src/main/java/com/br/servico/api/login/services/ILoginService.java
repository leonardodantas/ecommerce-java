package com.br.servico.api.login.services;

import com.br.servico.api.login.models.request.UserLoginRequestDTO;
import com.br.servico.api.login.models.response.TokenResponseDTO;

public interface ILoginService {
    TokenResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO);
}
