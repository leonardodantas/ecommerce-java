package com.br.servico.api.login.services.impl;

import com.br.servico.api.login.models.entity.Role;
import com.br.servico.api.login.models.request.RoleRequest;
import com.br.servico.api.login.repository.IRoleRepository;
import com.br.servico.api.login.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    private static final String ROLE_NOT_EXIST = "Role não existe no banco de dados";

    @Override
    public Role findRoleByName(RoleRequest role){
        return this.findRoleByNameInDataBase(role)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLE_NOT_EXIST));
    }

    private Optional<Role> findRoleByNameInDataBase(RoleRequest role) {
        try {
            return roleRepository.findByName(role.getRole());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
