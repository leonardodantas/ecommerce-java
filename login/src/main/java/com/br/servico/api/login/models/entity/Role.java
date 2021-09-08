package com.br.servico.api.login.models.entity;

import com.br.servico.api.login.models.request.RoleRequest;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private String id;

    @Column(name = "nome", length = 20)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
