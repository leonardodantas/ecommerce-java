package com.br.servico.api.login.models.request;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UserRequestDTO {

    @NotBlank
    private String name;
    @NotBlank @Email
    private String email;
    @Length(min = 6, max = 6)
    private String password;
    @NotNull
    private RoleRequest role;

}
