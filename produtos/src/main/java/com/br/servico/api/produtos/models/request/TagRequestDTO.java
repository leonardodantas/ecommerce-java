package com.br.servico.api.produtos.models.request;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;


@Getter
public class TagRequestDTO {

    @Length(min = 3, max = 6)
    private String tag;
    @Length(min = 3, max = 120)
    private String description;
}
