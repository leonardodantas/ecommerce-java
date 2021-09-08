package com.br.servico.api.carrinho.models.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductRestDTO {

    private String id;
    private String name;
    private BigDecimal price;
}
