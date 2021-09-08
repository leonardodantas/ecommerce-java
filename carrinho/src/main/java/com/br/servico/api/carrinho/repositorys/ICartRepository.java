package com.br.servico.api.carrinho.repositorys;

import com.br.servico.api.carrinho.models.entity.Cart;
import com.br.servico.api.carrinho.models.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUser(User user);
}
