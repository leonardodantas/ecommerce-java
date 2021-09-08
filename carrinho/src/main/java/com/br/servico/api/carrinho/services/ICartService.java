package com.br.servico.api.carrinho.services;

import com.br.servico.api.carrinho.models.response.CartResponseDTO;
import com.br.servico.api.carrinho.models.response.ProductResponseDTO;
import com.br.servico.api.carrinho.models.request.ProductRequestDTO;

public interface ICartService {
    ProductResponseDTO addProductInCart(ProductRequestDTO productRequestDTO);
    CartResponseDTO getCartUser();
    CartResponseDTO removeProductInCart(ProductRequestDTO productRequestDTO);
    CartResponseDTO removeProductInCart(String idProduct);
}
