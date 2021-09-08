package com.br.servico.api.login.services;

import com.br.servico.api.login.models.entity.User;
import com.br.servico.api.login.models.request.UserRequestDTO;
import com.br.servico.api.login.models.response.UserResponseDTO;

public interface IUserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    User findById(String id);
    User findByCredentialsEmail(String email);
    UserResponseDTO getUser();
}
