package com.br.servico.api.carrinho.models.response;

import com.br.servico.api.carrinho.models.entity.Cart;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponseDTO {

    private final String id;
    private final List<ProductResponseDTO> products;
    private final int qtdProduct;
    private final BigDecimal totalAmount;

    private CartResponseDTO(Cart cart) {
        this.id = cart.getId();
        this.products = cart.getProducts().stream().map(ProductResponseDTO::of).collect(Collectors.toUnmodifiableList());
        this.qtdProduct = cart.getQtdProduct();
        this.totalAmount = cart.getTotalAmount();
    }

    public static CartResponseDTO of(Cart cart) {
        return new CartResponseDTO(cart);
    }
}
