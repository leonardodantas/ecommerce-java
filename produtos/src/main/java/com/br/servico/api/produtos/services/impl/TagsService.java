package com.br.servico.api.produtos.services.impl;

import com.br.servico.api.produtos.models.entity.Tags;
import com.br.servico.api.produtos.models.request.TagRequestDTO;
import com.br.servico.api.produtos.models.request.TagsRequestDTO;
import com.br.servico.api.produtos.models.response.PageTagsResponseDTO;
import com.br.servico.api.produtos.models.response.TagsResponseDTO;
import com.br.servico.api.produtos.repositorys.ITagsRepository;
import com.br.servico.api.produtos.services.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagsService implements ITagsService {

    @Autowired
    private ITagsRepository tagsRepository;

    private static final String NOT_FOUND_TAG = "Tag não registada: ";
    private static final String ERROR_TAG = "Erro ao inserir TAG";

    @Override
    public List<TagsRequestDTO> verifyIdTags(List<TagsRequestDTO> tagsRequestDTOS){
        tagsRequestDTOS = eliminateDuplicates(tagsRequestDTOS);
        tagsRequestDTOS.forEach(this::getIdTagsInDataBase);
        return tagsRequestDTOS;
    }

    @Override
    public TagsResponseDTO createTag(TagRequestDTO tagsRequestDTO) {
        Tags tag = Tags.of(tagsRequestDTO);
        Tags tagSave = saveTagInDataBase(tag);
        return TagsResponseDTO.of(tagSave);
    }

    @Override
    public TagsResponseDTO getTagId(String id) {
        Tags tag = getIdTagsInDataBase(TagsRequestDTO.of(id));
        return TagsResponseDTO.of(tag);
    }

    @Override
    public PageTagsResponseDTO getAllTags(Pageable pageable) {
        Page<Tags> tagsPage = getAllTagsInDataBase(pageable);
        return PageTagsResponseDTO.of(tagsPage);
    }

    private Page<Tags> getAllTagsInDataBase(Pageable pageable){
        try {
            return tagsRepository.findAll(pageable);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Tags saveTagInDataBase(Tags tag){
        try {
            return tagsRepository.save(tag);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_TAG);
        }
    }

    private List<TagsRequestDTO> eliminateDuplicates(List<TagsRequestDTO> tagsRequestDTOS) {
        Set<String> tags = tagsRequestDTOS
                .stream()
                .map(TagsRequestDTO::getIdTag)
                .collect(Collectors.toSet());

        return tags.stream().map(TagsRequestDTO::of).collect(Collectors.toUnmodifiableList());
    }

    private Tags getIdTagsInDataBase(TagsRequestDTO tags) {
        return tagsRepository.findById(tags.getIdTag())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, NOT_FOUND_TAG + tags.getIdTag()));
    }
}
