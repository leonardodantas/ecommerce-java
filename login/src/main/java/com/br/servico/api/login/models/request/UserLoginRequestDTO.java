package com.br.servico.api.login.models.request;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginRequestDTO {

    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
