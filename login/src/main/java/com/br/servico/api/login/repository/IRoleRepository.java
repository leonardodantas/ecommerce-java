package com.br.servico.api.login.repository;

import com.br.servico.api.login.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String role);
}
