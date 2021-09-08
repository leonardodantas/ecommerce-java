package com.br.servico.api.carrinho.models.entity;

import com.br.servico.api.carrinho.models.request.ProductRequestDTO;
import com.br.servico.api.carrinho.models.response.ProductDataRestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private BigDecimal amount;
    private int qtd;
    private BigDecimal totalAmount;

    private Product(ProductDataRestDTO productDataRestDTO, ProductRequestDTO productRequestDTO) {
        this.id = productDataRestDTO.getId();
        this.name = productDataRestDTO.getName();
        this.amount = productDataRestDTO.getPrice();
        this.qtd = productRequestDTO.getQtd();
        this.totalAmount = this.amount.multiply(BigDecimal.valueOf(qtd));
    }

    public static Product of(ProductDataRestDTO productDataRestDTO, ProductRequestDTO productRequestDTO) {
        return new Product(productDataRestDTO, productRequestDTO);
    }

    public void removeQtd(int qtd){
        this.qtd =- qtd;
    }
}
