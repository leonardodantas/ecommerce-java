package com.br.servico.api.carrinho.models.response;

import com.br.servico.api.carrinho.models.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponseDTO {

    private final String id;
    private final String name;
    private final BigDecimal amount;
    private final int qtd;
    private final BigDecimal totalAmount;

    private ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.qtd = product.getQtd();
        this.totalAmount = product.getTotalAmount();
    }

    public static ProductResponseDTO of(Product product) {
        return new ProductResponseDTO(product);
    }
}
