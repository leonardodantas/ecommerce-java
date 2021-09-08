package com.br.servico.api.login.models.entity;

import com.br.servico.api.login.models.request.UserRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    private String id;
    @Column(name = "nome", length = 120)
    private String name;
    @Embedded
    private Credentials credentials;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    private User(UserRequestDTO userRequestDTO, Role role) {
        this.id = UUID.randomUUID().toString();
        this.name = userRequestDTO.getName();
        this.credentials = Credentials.of(userRequestDTO);
        this.roles = Collections.singletonList(role);
    }

    public static User of(UserRequestDTO userRequestDTO, Role role) {
        return new User(userRequestDTO, role);
    }

    public String getEmail() {
        return credentials.getEmail();
    }

    public String getPassword() {
        return credentials.getPassword();
    }
}
