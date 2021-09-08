package com.br.servico.api.login.models.response;

import com.br.servico.api.login.models.entity.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResourceOwnerResponseDTO {

    private String name;
    private String password;
    private List<RoleResponseDTO> roles;

    private ResourceOwnerResponseDTO(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(RoleResponseDTO::of).collect(Collectors.toUnmodifiableList());
    }

    public static ResourceOwnerResponseDTO of(User user) {
        return new ResourceOwnerResponseDTO(user);
    }
}
