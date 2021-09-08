package com.br.servico.api.login.models.response;

import com.br.servico.api.login.models.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final String id;
    private final String name;
    private final String email;

    private UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public static UserResponseDTO of(User user){
        return new UserResponseDTO(user);
    }
}
