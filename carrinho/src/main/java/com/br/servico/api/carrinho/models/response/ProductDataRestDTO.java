package com.br.servico.api.carrinho.models.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductDataRestDTO {

    private ProductRestDTO data;

    public String getId(){
        return data.getId();
    }

    public String getName(){
        return data.getName();
    }

    public BigDecimal getPrice(){
        return data.getPrice();
    }
}
