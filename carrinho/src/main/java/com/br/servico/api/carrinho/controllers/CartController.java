package com.br.servico.api.carrinho.controllers;

import com.br.servico.api.carrinho.models.request.ProductRequestDTO;
import com.br.servico.api.carrinho.models.response.CartResponseDTO;
import com.br.servico.api.carrinho.models.response.ProductResponseDTO;
import com.br.servico.api.carrinho.services.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Carrinho")
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Carrinho", value = "Adiciona um novo produto no carrinho")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Returns", response = ProductResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<ProductResponseDTO> addProductInCart(@Valid @RequestBody ProductRequestDTO body){
        ProductResponseDTO response = cartService.addProductInCart(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(tags = "Carrinho", value = "Recupera o carrinho do usuario atraves de seu token")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Returns", response = CartResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> getCartUser(){
        CartResponseDTO response = cartService.getCartUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(tags = "Carrinho", value = "Remove uma quantidade de produto do carrinho")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Returns", response = CartResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<CartResponseDTO> removeProductInCart(@Valid @RequestBody ProductRequestDTO body){
        CartResponseDTO response = cartService.removeProductInCart(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/product")
    @ApiOperation(tags = "Carrinho", value = "Remove todo o produto do carrinho")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Returns", response = CartResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<CartResponseDTO> removeProductInCart(@RequestParam String idProduct){
        CartResponseDTO response = cartService.removeProductInCart(idProduct);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } 
}
