package com.br.servico.api.produtos.models.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class    TagsRequestDTO {

    @NotBlank(message = "Tag deve ser enviada")
    private String idTag;

    public TagsRequestDTO(String tag) {
        this.idTag = tag;
    }

    public static TagsRequestDTO of(String tag) {
        return new TagsRequestDTO(tag);
    }
}
