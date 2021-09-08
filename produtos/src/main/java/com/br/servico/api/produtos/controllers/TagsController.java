package com.br.servico.api.produtos.controllers;

import com.br.servico.api.produtos.models.request.TagRequestDTO;
import com.br.servico.api.produtos.models.response.*;
import com.br.servico.api.produtos.services.ITagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Tags")
@RestController
@RequestMapping("/v1/tags")
public class TagsController {

    @Autowired
    private ITagsService tagsService;

    @PostMapping
    @ApiOperation(value = "Cria uma nova tag", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Tag criada com sucesso", response = TagsResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<DataResponse> createTag(@Valid @RequestBody TagRequestDTO body){
        TagsResponseDTO response = tagsService.createTag(body);
        return new ResponseEntity<>(DataResponse.builder().data(response).build(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Recuperar tag pelo id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Tag recuperada", response = TagsResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<DataResponse> getTagId(@PathVariable String id){
        TagsResponseDTO response = tagsService.getTagId(id);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Recuperar todas as tags")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Lista de tags recuperadas", response = PageTagsResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<PageTagsResponseDTO> getAllTags(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page, size);
        PageTagsResponseDTO response = tagsService.getAllTags(pageable);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
