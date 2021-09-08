package com.br.servico.api.produtos.controllers;

import com.br.servico.api.produtos.models.request.ProductRequestDTO;
import com.br.servico.api.produtos.models.response.DataResponse;
import com.br.servico.api.produtos.models.response.ErrorResponseDTO;
import com.br.servico.api.produtos.models.response.PageProductsResponseDTO;
import com.br.servico.api.produtos.models.response.ProductResponseDTO;
import com.br.servico.api.produtos.services.IproductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @Autowired
    private IproductService productService;

    @PostMapping
    @ApiOperation(value = "Adicionar um novo produto no sistema", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Produto adicionado", response = ProductResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
    })
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO body){
        ProductResponseDTO response = productService.addProduct(body);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Recuperar um produto pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Produto recuperado", response = ProductResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Produto não encontrado", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<ProductResponseDTO> getProdutId(@PathVariable String id){
        ProductResponseDTO response = productService.getProdutId(id);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Recuperar todos os produtos")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Produto recuperado", response = PageProductsResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Produto não encontrado", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<PageProductsResponseDTO> getAllProduts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        PageRequest pageable = PageRequest.of(page, size);
        PageProductsResponseDTO response = productService.getAllProduts(pageable);
        return new ResponseEntity(DataResponse.builder().data(response).build(), HttpStatus.OK);
    }
}
