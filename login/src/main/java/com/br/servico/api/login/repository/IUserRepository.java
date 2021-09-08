package com.br.servico.api.login.repository;

import com.br.servico.api.login.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByCredentialsEmail(String email);
}
