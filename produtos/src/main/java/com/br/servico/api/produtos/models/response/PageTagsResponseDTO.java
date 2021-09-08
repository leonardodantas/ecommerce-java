package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import com.br.servico.api.produtos.models.entity.Tags;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PageTagsResponseDTO extends PageResponse {

    private final List<TagsResponseDTO> body;

    private PageTagsResponseDTO(Page<Tags> tags) {
        super(tags.getPageable().getPageNumber(), tags.getPageable().getPageSize(), tags.getTotalElements());
        this.body = tags.getContent().stream().map(TagsResponseDTO::of).collect(Collectors.toUnmodifiableList());
    }

    public static PageTagsResponseDTO of(Page<Tags> tags) {
        return new PageTagsResponseDTO(tags);
    }
}
