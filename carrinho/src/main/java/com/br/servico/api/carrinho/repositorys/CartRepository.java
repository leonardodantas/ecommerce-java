package com.br.servico.api.carrinho.repositorys;

import com.br.servico.api.carrinho.models.entity.Cart;
import com.br.servico.api.carrinho.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public class CartRepository {

    @Autowired
    private ICartRepository cartRepository;

    public Optional<Cart> findCart(User user) {
        try {
            return cartRepository.findByUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Cart saveCart(Cart cart){
        try {
            return this.cartRepository.save(cart);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
