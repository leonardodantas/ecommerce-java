package com.br.servico.api.login.models.entity;

import com.br.servico.api.login.models.request.UserRequestDTO;
import com.br.servico.api.login.utils.BCryptPasswordEncoderUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class Credentials {

    @Column(name = "email", length = 120, unique = true)
    private String email;
    @Column(name = "senha", length = 65)
    private String password;

    private Credentials(UserRequestDTO userRequestDTO) {
        this.email = userRequestDTO.getEmail();
        this.password = BCryptPasswordEncoderUtils.encode(userRequestDTO.getPassword());
    }

    public static Credentials of(UserRequestDTO userRequestDTO) {
        return new Credentials(userRequestDTO);
    }
}
