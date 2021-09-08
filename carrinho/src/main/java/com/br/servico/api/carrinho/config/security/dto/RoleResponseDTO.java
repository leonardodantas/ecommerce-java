package com.br.servico.api.carrinho.config.security.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class RoleResponseDTO implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
