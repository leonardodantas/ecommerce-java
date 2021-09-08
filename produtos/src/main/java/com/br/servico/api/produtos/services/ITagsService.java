package com.br.servico.api.produtos.services;

import com.br.servico.api.produtos.models.request.TagRequestDTO;
import com.br.servico.api.produtos.models.request.TagsRequestDTO;
import com.br.servico.api.produtos.models.response.PageTagsResponseDTO;
import com.br.servico.api.produtos.models.response.TagsResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITagsService {

    List<TagsRequestDTO> verifyIdTags(List<TagsRequestDTO> tagsRequestDTOS);
    TagsResponseDTO createTag(TagRequestDTO tagsRequestDTO);
    TagsResponseDTO getTagId(String id);
    PageTagsResponseDTO getAllTags(Pageable pageable);
}
