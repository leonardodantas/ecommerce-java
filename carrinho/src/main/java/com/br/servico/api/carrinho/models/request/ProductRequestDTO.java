package com.br.servico.api.carrinho.models.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class ProductRequestDTO {

    @NotBlank
    private String id;
    @Min(1)
    private int qtd;
}
