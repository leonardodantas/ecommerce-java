package com.br.servico.api.login.services;

import com.br.servico.api.login.models.entity.Role;
import com.br.servico.api.login.models.request.RoleRequest;

public interface IRoleService {

    Role findRoleByName(RoleRequest role);
}
