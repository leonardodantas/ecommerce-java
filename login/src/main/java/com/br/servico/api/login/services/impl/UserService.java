package com.br.servico.api.login.services.impl;

import com.br.servico.api.login.config.security.TokenService;
import com.br.servico.api.login.models.entity.Role;
import com.br.servico.api.login.models.entity.User;
import com.br.servico.api.login.models.request.UserRequestDTO;
import com.br.servico.api.login.models.response.UserResponseDTO;
import com.br.servico.api.login.repository.IUserRepository;
import com.br.servico.api.login.services.IRoleService;
import com.br.servico.api.login.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private TokenService tokenService;

    private static final String USER_NOT_EXIST = "User not exist";

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Role role = roleService.findRoleByName(userRequestDTO.getRole());
        User user = User.of(userRequestDTO, role);
        User userSave = saveUserInDataBase(user);
        return UserResponseDTO.of(userSave);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_EXIST));
    }

    @Override
    public User findByCredentialsEmail(String email) {
        return userRepository.findByCredentialsEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_EXIST));
    }

    @Override
    public UserResponseDTO getUser() {
        String idUser = tokenService.getIdUser();
        User user = findById(idUser);
        return UserResponseDTO.of(user);
    }

    private User saveUserInDataBase(User user){
        try {
            return userRepository.save(user);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
