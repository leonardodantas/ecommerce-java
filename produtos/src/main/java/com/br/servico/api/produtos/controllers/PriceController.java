package com.br.servico.api.produtos.controllers;

import com.br.servico.api.produtos.models.response.*;
import com.br.servico.api.produtos.services.IPriceHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.HttpURLConnection;

@Api(tags = "Preços")
@RestController
@RequestMapping("/v1/prices")
public class PriceController {

    @Autowired
    private IPriceHistoryService priceHistoryService;

    @PutMapping
    @ApiOperation(value = "Atualizar preço de um produto", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Preço do produto alterado", response = PriceProductResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
    })
    public ResponseEntity<PriceProductResponseDTO> updatePriceProduct(@RequestParam String id, @RequestParam BigDecimal price){
        PriceProductResponseDTO response = priceHistoryService.updatePriceProduct(id, price);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    @ApiOperation(value = "Retornar historico de preços de um produto", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Historico de preços", response = PagePriceHistoryResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<PagePriceHistoryResponseDTO> getAllProductPrice(@PathVariable String id,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "20") int size){
        PageRequest pageable = PageRequest.of(page, size);
        PagePriceHistoryResponseDTO response = priceHistoryService.getAllProductPrice(id, pageable);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.OK);
    }
}
