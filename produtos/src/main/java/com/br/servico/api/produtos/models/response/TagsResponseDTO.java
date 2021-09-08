package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.Tags;
import lombok.Getter;

@Getter
public class TagsResponseDTO {

    private final String id;
    private final String tag;
    private final String description;

    private TagsResponseDTO(Tags tags) {
        this.id = tags.getId();
        this.tag = tags.getTag();
        this.description = tags.getDescription();
    }

    public static TagsResponseDTO of(Tags tags) {
        return new TagsResponseDTO(tags);
    }

    public static TagsResponseDTO of(TagsResponseDTO tags) {
        return null;
    }
}
