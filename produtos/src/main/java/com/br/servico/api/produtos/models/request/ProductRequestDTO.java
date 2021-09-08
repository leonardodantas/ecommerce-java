package com.br.servico.api.produtos.models.request;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class ProductRequestDTO {

    @NotBlank(message = "Produto deve possuir nome")
    private String name;
    @Min(value = 1)
    private BigDecimal price;
    @Valid
    private List<TagsRequestDTO> tags;
}
