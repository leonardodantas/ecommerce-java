package com.br.servico.api.produtos.controllers;

import com.br.servico.api.produtos.models.request.PromotionPriceRequestDTO;
import com.br.servico.api.produtos.models.response.*;
import com.br.servico.api.produtos.services.IPromotionService;
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

@Api(tags = "Promoções")
@RestController
@RequestMapping("/v1/promotion")
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    @PostMapping("/price")
    @ApiOperation(value = "Criar uma nova promoção", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Promoção criada", response = PromotionResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
    })
    public ResponseEntity<PromotionResponseDTO> createPromotion(@Valid @RequestBody PromotionPriceRequestDTO body){
        PromotionResponseDTO response = promotionService.createPromotion(body);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.CREATED);
    }


    @GetMapping("/product/{id}")
    @ApiOperation(value = "Recuperar todas as promoções de um produto pelo seu id", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Lista de promoções de um produto", response = PagePromotionResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<PromotionResponseDTO> getAllPromotion(@PathVariable(value = "id") String idProduct, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page,size);
        PagePromotionResponseDTO response = promotionService.getAllPromotion(idProduct, pageable);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.CREATED);
    }

}
