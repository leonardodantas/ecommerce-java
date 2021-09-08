package com.br.servico.api.login.models.response;

import com.br.servico.api.login.models.entity.Role;
import lombok.Getter;

@Getter
public class RoleResponseDTO {

    private final String name;

    private RoleResponseDTO(Role role) {
        this.name = role.getAuthority();
    }

    public static RoleResponseDTO of(Role role) {
        return new RoleResponseDTO(role);
    }
}
